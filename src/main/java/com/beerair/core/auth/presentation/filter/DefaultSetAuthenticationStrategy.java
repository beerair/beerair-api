package com.beerair.core.auth.presentation.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class DefaultSetAuthenticationStrategy implements SetAuthenticationStrategy {
    @Override
    public void set(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
