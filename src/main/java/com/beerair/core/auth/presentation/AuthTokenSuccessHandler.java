package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.RefreshTokenService;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.error.exception.auth.BadLoginRequestException;
import lombok.Builder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RedisTemplate<String, Object> redisTemplate;
    private final String successRedirectUri;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final RefreshTokenService refreshTokenService;

    @Builder
    private AuthTokenSuccessHandler(
            RedisTemplate<String, Object> redisTemplate,
            String successRedirectUri,
            AuthTokenCrypto accessTokenCrypto,
            AuthTokenCrypto refreshTokenCrypto,
            RefreshTokenService refreshTokenService
    ) {
        this.redisTemplate = redisTemplate;
        this.successRedirectUri = successRedirectUri;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
        this.refreshTokenService = refreshTokenService;
    }

    @Transactional
    @Override
    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            throw new BadLoginRequestException();
        }
        var authTokenAuthentication = AuthTokenAuthentication.from(
                (OAuth2AuthenticationToken) authentication
        );
        String access = accessTokenCrypto.encrypt(authTokenAuthentication);
        String refresh = refreshTokenCrypto.encrypt(authTokenAuthentication);

        String memberId = authTokenAuthentication.getLoggedInUser().getId();
        persist(memberId, access, refresh);

        var location = location(request, access, refresh);
        response.sendRedirect(location);
    }

    private void persist(String memberId, String access, String refresh) {
        redisTemplate.opsForValue().set("authToken:" + memberId, access);
        refreshTokenService.renew(memberId, refresh);
    }

    private String location(HttpServletRequest request, String access, String refresh) {
        return UriComponentsBuilder.fromUriString(successRedirectUri)
                .query(request.getQueryString())
                .queryParam("accessToken", access)
                .queryParam("refreshToken", refresh)
                .build()
                .toUriString();
    }
}
