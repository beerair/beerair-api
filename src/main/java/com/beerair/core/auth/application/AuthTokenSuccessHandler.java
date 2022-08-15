package com.beerair.core.auth.application;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.beerair.core.auth.application.AuthTokenAuthenticationFilter.TOKEN_TYPE;

@Component
public final class AuthTokenSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthTokenProvider authTokenProvider;

    public AuthTokenSuccessHandler(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        var authToken = authTokenProvider.encode(authentication);
        response.setHeader("authorization", TOKEN_TYPE + " " + authToken);
    }
}
