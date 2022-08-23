package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.error.exception.auth.BadLoginRequestException;
import com.beerair.core.member.domain.vo.Role;
import lombok.Builder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthTokenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Collection<? extends GrantedAuthority> MEMBER_AUTHORITIES;

    static {
        MEMBER_AUTHORITIES = Role.MEMBER
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private final RedisTemplate<String, Object> redisTemplate;
    private final String successRedirectUri;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final AuthTokenService refreshTokenService;
    private final AuthTokenFailureHandler failureHandler;

    @Builder
    private AuthTokenSuccessHandler(
            RedisTemplate<String, Object> redisTemplate,
            String successRedirectUri,
            AuthTokenCrypto accessTokenCrypto,
            AuthTokenCrypto refreshTokenCrypto,
            AuthTokenService refreshTokenService,
            AuthTokenFailureHandler failureHandler
    ) {
        this.redisTemplate = redisTemplate;
        this.successRedirectUri = successRedirectUri;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
        this.refreshTokenService = refreshTokenService;
        this.failureHandler = failureHandler;
    }

    @Transactional
    @Override
    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            failureHandler.onAuthenticationFailure(request, response, new BadLoginRequestException());
            return;
        }
        var authTokenAuthentication = AuthTokenAuthentication.create(
                (OAuth2AuthenticationToken) authentication
        );
        String access = accessToken(authTokenAuthentication);
        String refresh = refreshToken(authTokenAuthentication);

        String memberId = authTokenAuthentication.getLoggedInUser().getId();
        persist(memberId, access, refresh);

        var location = location(request, access, refresh);
        response.sendRedirect(location);
    }

    private String accessToken(AuthTokenAuthentication authTokenAuthentication) {
        return accessTokenCrypto.encrypt(authTokenAuthentication);
    }

    private String refreshToken(AuthTokenAuthentication authTokenAuthentication) {
        if (authTokenAuthentication.getAuthorities().containsAll(MEMBER_AUTHORITIES)) {
            return accessTokenCrypto.encrypt(authTokenAuthentication);
        }
        return null;
    }

    private void persist(String memberId, String access, String refresh) {
        if (Objects.nonNull(refresh)) {
            refreshTokenService.issue(memberId, refresh);
        }
        redisTemplate.opsForValue().set("authToken:" + memberId, access);
    }

    private String location(HttpServletRequest request, String access, String refresh) {
        return UriComponentsBuilder.fromUriString(successRedirectUri)
                .query(request.getQueryString())
                .queryParam("accessToken", access)
                .queryParamIfPresent("refreshToken", Optional.ofNullable(refresh))
                .build()
                .toUriString();
    }
}
