package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.dto.response.TokenResponse;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.error.exception.auth.RefreshTokenNotFoundException;
import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            @Qualifier(TokenType.ACCESS) AuthTokenCrypto accessTokenCrypto,
            @Qualifier(TokenType.REFRESH) AuthTokenCrypto refreshTokenCrypto
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
    }

    public void create(String token) {
        refreshTokenRepository.save(new RefreshToken(token));
    }

    public TokenResponse issueToken(String token) {
        get(token).use();

        AuthTokenAuthentication authentication = refreshTokenCrypto.decrypt(token);
        var newAccess = accessTokenCrypto.encrypt(authentication);
        var newRefresh = refreshTokenCrypto.encrypt(authentication);
        return new TokenResponse(
                newAccess, newRefresh
        );
    }

    public RefreshToken get(String token) {
        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(RefreshTokenNotFoundException::new);
    }
}
