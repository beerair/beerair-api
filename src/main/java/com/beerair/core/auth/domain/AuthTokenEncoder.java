package com.beerair.core.auth.domain;

import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

public interface AuthTokenEncoder {
    String encode(TokenType tokenType, Authentication authentication);

    String encode(TokenType tokenType, LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities);

    Collection<? extends GrantedAuthority> getAuthorities(String token);

    LoggedInUser getLoggedInUser(String token);

    Date getExpired(String token);
}
