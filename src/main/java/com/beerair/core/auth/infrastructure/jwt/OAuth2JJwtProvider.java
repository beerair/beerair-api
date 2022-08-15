package com.beerair.core.auth.infrastructure.jwt;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

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
        return oAuth2Member(authentication).getId();
    }

    private OAuth2Member oAuth2Member(Authentication authentication) {
        return (OAuth2Member) authentication.getPrincipal();
    }

    @Override
    protected List<String> getAuthorities(Authentication authentication) {
        return oAuth2Member(authentication)
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
