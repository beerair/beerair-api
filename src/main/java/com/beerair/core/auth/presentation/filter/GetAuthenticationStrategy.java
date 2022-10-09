package com.beerair.core.auth.presentation.filter;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface GetAuthenticationStrategy {
    Authentication get(HttpServletRequest request);
}
