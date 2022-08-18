package com.beerair.core.auth.domain;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.dto.LoggedInUser;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Collection;
import java.util.Date;

@Getter
public class AuthTokenAuthentication extends AbstractAuthenticationToken {
    private final LoggedInUser loggedInUser;
    private final Date expired;

    private AuthTokenAuthentication(LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities, Date expired) {
        super(authorities);
        this.loggedInUser = loggedInUser;
        this.expired = expired;
    }

    public static AuthTokenAuthentication create(OAuth2AuthenticationToken authentication) {
        OAuth2Member oAuth2Member = (OAuth2Member) authentication.getPrincipal();
        return new AuthTokenAuthentication(oAuth2Member, authentication.getAuthorities(), null);
    }

    public static AuthTokenAuthentication from(LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities, Date expired) {
        return new AuthTokenAuthentication(loggedInUser, authorities, expired);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public LoggedInUser getPrincipal() {
        return loggedInUser;
    }
}
