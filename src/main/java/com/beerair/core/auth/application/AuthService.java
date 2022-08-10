package com.beerair.core.auth.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.beerair.core.auth.application.dto.request.OAuth2TokenRequest;
import com.beerair.core.auth.application.dto.response.AuthResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserDetailsService userDetailsService;

    public AuthResponse loginBySocial(OAuth2TokenRequest request) {
        return null;
    }
}
