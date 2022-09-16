package com.beerair.core.auth.presentation.loginhandler;

import com.beerair.core.auth.application.RefreshTokenService;
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
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final AuthTokenFailureHandler failureHandler;
    private final TokenDelivery tokenDeliver;
    private final RefreshTokenService refreshTokenService;

    @Builder
    private AuthTokenSuccessHandler(
            String successRedirectUri,
            AuthTokenCrypto accessTokenCrypto,
            AuthTokenCrypto refreshTokenCrypto,
            RefreshTokenService refreshTokenService,
            AuthTokenFailureHandler failureHandler,
            TokenDelivery tokenDeliver
    ) {
        this.successRedirectUri = successRedirectUri;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
        this.failureHandler = failureHandler;
        this.tokenDeliver = tokenDeliver;
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
            failureHandler.onAuthenticationFailure(request, response, new BadLoginRequestException());
            return;
        }
        var authTokenAuthentication = AuthTokenAuthentication.create(
                (OAuth2AuthenticationToken) authentication
        );
        var access = accessToken(authTokenAuthentication);
        var refresh = refreshToken(authTokenAuthentication);
        var memberId = authTokenAuthentication.getLoggedInUser().getId();

        refreshTokenService.issue(memberId, refresh);
        tokenDeliver.deliver(request, response, access, refresh);
        response.sendRedirect(successRedirectUri);
    }

    private AuthToken accessToken(AuthTokenAuthentication authTokenAuthentication) {
        return accessTokenCrypto.encrypt(authTokenAuthentication);
    }

    private AuthToken refreshToken(AuthTokenAuthentication authTokenAuthentication) {
        return refreshTokenCrypto.encrypt(authTokenAuthentication);
    }
}
