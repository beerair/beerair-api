package com.beerair.core.auth.presentation.loginhandler;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.error.exception.auth.BadLoginRequestException;
import lombok.Builder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final String successRedirectUri;
    private final RedisTemplate<String, Object> redisTemplate;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final AuthTokenService refreshTokenService;
    private final AuthTokenFailureHandler failureHandler;
    private final TokenDelivery tokenDeliver;

    @Builder
    private AuthTokenSuccessHandler(
            RedisTemplate<String, Object> redisTemplate,
            String successRedirectUri,
            AuthTokenCrypto accessTokenCrypto,
            AuthTokenCrypto refreshTokenCrypto,
            AuthTokenService refreshTokenService,
            AuthTokenFailureHandler failureHandler,
            TokenDelivery tokenDeliver
    ) {
        this.successRedirectUri = successRedirectUri;
        this.redisTemplate = redisTemplate;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
        this.refreshTokenService = refreshTokenService;
        this.failureHandler = failureHandler;
        this.tokenDeliver = tokenDeliver;
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
        var access = accessToken(authTokenAuthentication);
        var refresh = refreshToken(authTokenAuthentication);
        var memberId = authTokenAuthentication.getLoggedInUser().getId();
        persistToken(memberId, access, refresh);

        tokenDeliver.deliver(request, response, access, refresh);
        response.sendRedirect(successRedirectUri);
    }

    private AuthToken accessToken(AuthTokenAuthentication authTokenAuthentication) {
        return accessTokenCrypto.encrypt(authTokenAuthentication);
    }

    private AuthToken refreshToken(AuthTokenAuthentication authTokenAuthentication) {
        return refreshTokenCrypto.encrypt(authTokenAuthentication);
    }

    private void persistToken(String memberId, AuthToken access, AuthToken refresh) {
        redisTemplate.opsForValue().set("authToken:" + memberId, access);
        refreshTokenService.issue(memberId, refresh.getToken());
    }
}
