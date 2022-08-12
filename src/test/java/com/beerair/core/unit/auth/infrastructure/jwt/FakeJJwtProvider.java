package com.beerair.core.unit.auth.infrastructure.jwt;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.beerair.core.auth.infrastructure.jwt.JJwtProvider;

import lombok.Setter;

@Setter
public class FakeJJwtProvider extends JJwtProvider {
    private boolean providable;
    private String id;
    private Collection<? extends GrantedAuthority> authorities;

    public FakeJJwtProvider(String signatureAlgorithm, String signatureKey, int expiration) {
        super(signatureAlgorithm, signatureKey, expiration);
    }

    @Override
    protected boolean isProvidable(Authentication authentication) {
        return providable;
    }

    @Override
    protected String getId(Authentication authentication) {
        return id;
    }

    @Override
    protected Collection<? extends GrantedAuthority> getAuthorities(Authentication authentication) {
        return authorities;
    }
}
