package com.beerair.core.auth.application;

import static com.beerair.core.auth.application.AuthTokenAuthenticationFilter.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public final class AuthTokenSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthTokenProvider authTokenProvider;

    public AuthTokenSuccessHandler(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        String authToken = authTokenProvider.encode(authentication);
        response.setHeader("authorization", TOKEN_TYPE + " " + authToken);
    }
}
