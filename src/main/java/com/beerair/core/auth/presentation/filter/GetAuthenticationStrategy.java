package com.beerair.core.auth.presentation.filter;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface GetAuthenticationStrategy {
    Authentication get(HttpServletRequest request);
}
