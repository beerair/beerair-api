package com.beerair.core.auth.domain;

import com.beerair.core.member.dto.LoggedInUser;
import lombok.Builder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthTokenAuthentication extends AbstractAuthenticationToken {
    private final String token;
    private final LoggedInUser loggedInUser;

    @Builder
    private AuthTokenAuthentication(Collection<? extends GrantedAuthority> authorities, String token, LoggedInUser loggedInUser) {
        super(authorities);
        this.token = token;
        this.loggedInUser = loggedInUser;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public LoggedInUser getPrincipal() {
        return loggedInUser;
    }
}
