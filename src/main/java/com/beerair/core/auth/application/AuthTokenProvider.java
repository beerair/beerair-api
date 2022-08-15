package com.beerair.core.auth.application;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface AuthTokenProvider {
    String encode(Authentication authentication);

    String getId(String token);

    Collection<? extends GrantedAuthority> getAuthorities(String token);
}
