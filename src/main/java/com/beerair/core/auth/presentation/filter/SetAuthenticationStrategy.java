package com.beerair.core.auth.presentation.filter;

import org.springframework.security.core.Authentication;

public interface SetAuthenticationStrategy {
    void set(Authentication authentication);
}
