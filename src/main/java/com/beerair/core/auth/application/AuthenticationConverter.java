package com.beerair.core.auth.application;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface AuthenticationConverter {
    Optional<Authentication> convert(HttpServletRequest request);
}
