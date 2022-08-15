package com.beerair.core.auth.domain;

import lombok.Builder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthTokenAuthentication extends AbstractAuthenticationToken {
    private final String token;
    private final String memberId;

    @Builder
    private AuthTokenAuthentication(
            String token,
            String memberId,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(authorities);
        this.token = token;
        this.memberId = memberId;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return memberId;
    }
}
