package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.RefreshTokenService;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.error.exception.auth.InvalidAuthException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class AuthTokenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final String successRedirectUri;
    private final AuthTokenEncoder accessTokenEncoder;
    private final AuthTokenEncoder refreshTokenEncoder;
    private final RefreshTokenService refreshTokenService;

    @Builder
    private AuthTokenSuccessHandler(
            @Value("${auth.success_redirect_uri}") String successRedirectUri,
            AuthTokenEncoder accessTokenEncoder,
            AuthTokenEncoder refreshTokenEncoder,
            RefreshTokenService refreshTokenService
    ) {
        this.successRedirectUri = successRedirectUri;
        this.accessTokenEncoder = accessTokenEncoder;
        this.refreshTokenEncoder = refreshTokenEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            throw new InvalidAuthException();
        }
        var authTokenAuthentication = (OAuth2AuthenticationToken) authentication;
        String access = accessTokenEncoder.encode(authTokenAuthentication);
        String refresh = refreshTokenEncoder.encode(authTokenAuthentication);

        refreshTokenService.create(refresh);

        var location = location(request, access, refresh);
        response.sendRedirect(location);
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
