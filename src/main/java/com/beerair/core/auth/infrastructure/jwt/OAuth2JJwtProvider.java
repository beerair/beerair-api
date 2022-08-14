package com.beerair.core.auth.infrastructure.jwt;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.beerair.core.auth.domain.OAuth2Member;

public final class OAuth2JJwtProvider extends JJwtProvider {
    public OAuth2JJwtProvider(String signatureAlgorithm, String signatureKey, int expiration) {
        super(signatureAlgorithm, signatureKey, expiration);
    }

    @Override
    protected boolean isProvidable(Authentication authentication) {
        return authentication.getPrincipal() instanceof OAuth2Member;
    }

    @Override
    protected String getId(Authentication authentication) {
        return oAuth2Member(authentication).getName();
    }

    private OAuth2Member oAuth2Member(Authentication authentication) {
        return (OAuth2Member) authentication.getPrincipal();
    }

    @Override
    protected Set<GrantedAuthority> getAuthorities(Authentication authentication) {
        return oAuth2Member(authentication).getAuthorities();
    }
}
