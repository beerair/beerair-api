package com.beerair.core.auth.application;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.beerair.core.auth.domain.TokenType;

@Component
public final class AuthTokenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final String successRedirectUri;
    private final AuthTokenProvider authTokenProvider;

    public AuthTokenSuccessHandler(
        @Value("${auth.success_redirect_uri}") String successRedirectUri,
        AuthTokenProvider authTokenProvider) {
        this.successRedirectUri = successRedirectUri;
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
                           Authentication authentication) throws IOException {
        String access = authTokenProvider.encode(TokenType.ACCESS, authentication);
        String refresh = authTokenProvider.encode(TokenType.REFRESH, authentication);

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
