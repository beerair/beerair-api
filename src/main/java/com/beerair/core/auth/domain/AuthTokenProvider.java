package com.beerair.core.auth.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.beerair.core.auth.domain.TokenType;

public interface AuthTokenProvider {
    String encode(TokenType tokenType, Authentication authentication);

    String encode(TokenType tokenType, String id, Collection<? extends GrantedAuthority> authorities);

    String getId(String token);

    Collection<? extends GrantedAuthority> getAuthorities(String token);

    Date getExpired(String token);
}
