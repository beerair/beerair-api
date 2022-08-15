package com.beerair.core.auth.application;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.beerair.core.auth.domain.TokenType;

public interface AuthTokenProvider {
    String encode(TokenType tokenType, Authentication authentication);

    String getId(String token);

    Collection<? extends GrantedAuthority> getAuthorities(String token);
}
