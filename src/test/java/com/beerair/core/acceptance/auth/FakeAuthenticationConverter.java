package com.beerair.core.acceptance.auth;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.beerair.core.auth.application.AuthenticationConverter;

public class FakeAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Optional<Authentication> convert(HttpServletRequest request) {
        return Optional.empty();
    }
}
