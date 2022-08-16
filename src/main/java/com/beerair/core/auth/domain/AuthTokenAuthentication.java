package com.beerair.core.auth.domain;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.dto.LoggedInUser;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Collection;

@Getter
public class AuthTokenAuthentication extends AbstractAuthenticationToken {
    private final LoggedInUser loggedInUser;

    public AuthTokenAuthentication(LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.loggedInUser = loggedInUser;
    }

    public static AuthTokenAuthentication from(OAuth2AuthenticationToken authentication) {
        OAuth2Member oAuth2Member = (OAuth2Member) authentication.getPrincipal();
        return new AuthTokenAuthentication(oAuth2Member, authentication.getAuthorities());
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
