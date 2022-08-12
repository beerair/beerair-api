package com.beerair.core.auth.infrastructure.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.beerair.core.auth.domain.AuthTokenProvider;

@Component
public final class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final SimpleUrlAuthenticationSuccessHandler delegate;
    private final AuthTokenProvider authTokenProvider;

    public OAuth2SuccessHandler(@Value("${oauth2.success_redirect_url}") String redirectUrl,
                                AuthTokenProvider authTokenProvider) {
        this.delegate = new SimpleUrlAuthenticationSuccessHandler(redirectUrl);
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String authToken = authTokenProvider.encode(authentication);
        response.addHeader("Authorization", "Bearer " + authToken);
        delegate.onAuthenticationSuccess(request, response, authentication);
    }
}
