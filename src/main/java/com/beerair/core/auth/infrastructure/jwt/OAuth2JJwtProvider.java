package com.beerair.core.auth.infrastructure.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;

public final class OAuth2JJwtProvider extends JJwtProvider {
    private final TokenType tokenType;
    public OAuth2JJwtProvider(TokenType tokenType, String signatureAlgorithm, String signatureKey, int expiration) {
        super(signatureAlgorithm, signatureKey, expiration);
        this.tokenType = tokenType;
    }

    @Override
    protected boolean isProvidable(TokenType tokenType, Authentication authentication) {
        return this.tokenType == tokenType &&
            authentication.getPrincipal() instanceof OAuth2Member;
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
