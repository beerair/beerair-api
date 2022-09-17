package com.beerair.core.auth.domain;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.dto.LoggedInMember;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Collection;
import java.util.Date;

@Getter
public class AuthTokenAuthentication extends AbstractAuthenticationToken {
    private final LoggedInMember loggedInUser;

    private AuthTokenAuthentication(
            LoggedInMember loggedInUser,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(authorities);
        this.loggedInUser = loggedInUser;
    }

    public static AuthTokenAuthentication create(OAuth2AuthenticationToken authentication) {
        var oAuth2Member = (OAuth2Member) authentication.getPrincipal();
        return new AuthTokenAuthentication(oAuth2Member, authentication.getAuthorities());
    }

    public static AuthTokenAuthentication from(
            LoggedInMember loggedInUser,
            Collection<? extends GrantedAuthority> authorities
    ) {
        return new AuthTokenAuthentication(loggedInUser, authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public LoggedInMember getPrincipal() {
        return loggedInUser;
    }
}
