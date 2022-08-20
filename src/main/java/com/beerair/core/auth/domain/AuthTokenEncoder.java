package com.beerair.core.auth.domain;

import com.beerair.core.error.exception.auth.InvalidAuthException;
import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Collection;
import java.util.Date;

public interface AuthTokenEncoder {
    default String encode(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            return encode(authentication);
        }
        throw new InvalidAuthException();
    }

    String encode(OAuth2AuthenticationToken authentication);

    String encode(LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities);

    Collection<? extends GrantedAuthority> getAuthorities(String token);

    LoggedInUser getLoggedInUser(String token);

    Date getExpired(String token);
}
