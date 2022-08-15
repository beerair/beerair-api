package com.beerair.core.unit.auth.infrastructure.jwt;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.jwt.JJwtProvider;

import lombok.Setter;

@Setter
public class FakeJJwtProvider extends JJwtProvider {
    private boolean providable;
    private String id;
    private List<String> authorities;

    public FakeJJwtProvider(String signatureAlgorithm, String signatureKey, int expiration) {
        super(signatureAlgorithm, signatureKey, expiration);
    }

    @Override
    protected boolean isProvidable(TokenType tokenType, Authentication authentication) {
        return providable;
    }

    @Override
    protected String getId(Authentication authentication) {
        return id;
    }
}
