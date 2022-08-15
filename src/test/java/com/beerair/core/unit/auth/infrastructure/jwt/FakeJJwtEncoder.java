package com.beerair.core.unit.auth.infrastructure.jwt;

import java.util.List;

import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.security.core.Authentication;

import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.jwt.JJwtEncoder;

import lombok.Setter;

@Setter
public class FakeJJwtEncoder extends JJwtEncoder {
    private boolean providable;
    private LoggedInUser loggedInUser;
    private List<String> authorities;

    public FakeJJwtEncoder(String signatureAlgorithm, String signatureKey, int expiration) {
        super(signatureAlgorithm, signatureKey, expiration);
    }

    @Override
    protected boolean isProvidable(TokenType tokenType, Authentication authentication) {
        return providable;
    }

    @Override
    protected LoggedInUser getLoggedInUser(Authentication authentication) {
        return loggedInUser;
    }
}
