package com.beerair.core.auth.domain;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public interface AuthTokenProvider {
    String encode(Authentication authentication);

    String getId(String token);

    Collection<? extends GrantedAuthority> getAuthorities(String token);
}
