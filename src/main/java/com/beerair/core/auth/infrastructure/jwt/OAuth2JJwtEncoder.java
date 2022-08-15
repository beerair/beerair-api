package com.beerair.core.auth.infrastructure.jwt;

import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.security.core.Authentication;

import java.util.Objects;

public final class OAuth2JJwtEncoder extends JJwtEncoder {
    private final TokenType tokenType;

    public OAuth2JJwtEncoder(
            TokenType tokenType,
            String signatureAlgorithm,
            String signatureKey,
            int expiration
    ) {
        super(signatureAlgorithm, signatureKey, expiration);
        this.tokenType = tokenType;
    }

    @Override
    protected boolean isProvidable(TokenType tokenType, Authentication authentication) {
        return this.tokenType == tokenType && (isTokenRequest(authentication) || isOAuth2MemberRequest(authentication));
    }

    private boolean isTokenRequest(Authentication authentication) {
        return Objects.isNull(authentication);
    }

    private boolean isOAuth2MemberRequest(Authentication authentication) {
        return authentication.getPrincipal() instanceof OAuth2Member;
    }

    @Override
    protected LoggedInUser getLoggedInUser(Authentication authentication) {
        return (LoggedInUser) authentication.getPrincipal();
    }


    private OAuth2Member oAuth2Member(Authentication authentication) {
        return (OAuth2Member) authentication.getPrincipal();
    }
}
