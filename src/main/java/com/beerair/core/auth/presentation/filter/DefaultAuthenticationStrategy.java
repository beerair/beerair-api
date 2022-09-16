package com.beerair.core.auth.presentation.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DefaultAuthenticationStrategy implements GetAuthenticationStrategy, SetAuthenticationStrategy {
    @Override
    public Authentication get(HttpServletRequest request) {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public void set(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
