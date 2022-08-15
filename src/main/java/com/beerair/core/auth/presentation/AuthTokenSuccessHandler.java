package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.RefreshTokenProvider;
import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.auth.domain.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class AuthTokenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final String successRedirectUri;
    private final AuthTokenEncoder authTokenProvider;
    private final RefreshTokenProvider refreshTokenService;

    public AuthTokenSuccessHandler(
            @Value("${auth.success_redirect_uri}") String successRedirectUri,
            AuthTokenEncoder authTokenProvider,
            RefreshTokenProvider refreshTokenService
    ) {
        this.successRedirectUri = successRedirectUri;
        this.authTokenProvider = authTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        String access = authTokenProvider.encode(TokenType.ACCESS, authentication);
        String refresh = authTokenProvider.encode(TokenType.REFRESH, authentication);

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
