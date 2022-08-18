package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.domain.TokenPurpose;
import com.beerair.core.auth.dto.response.TokenRefreshResponse;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.error.exception.auth.TokenNotFoundException;
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
            @Qualifier(TokenPurpose.ACCESS) AuthTokenCrypto accessTokenCrypto,
            @Qualifier(TokenPurpose.REFRESH) AuthTokenCrypto refreshTokenCrypto
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
    }

    public void renew(String memberId, String token) {
        refreshTokenRepository.findAllByMemberId(memberId)
                .forEach(RefreshToken::delete);
        refreshTokenRepository.save(new RefreshToken(memberId, token));
    }

    public TokenRefreshResponse issueByRefreshToken(String token) {
        get(token).use();

        AuthTokenAuthentication authentication = refreshTokenCrypto.decrypt(token);
        var newAccess = accessTokenCrypto.encrypt(authentication);
        var newRefresh = refreshTokenCrypto.encrypt(authentication);
        return new TokenRefreshResponse(
                newAccess, newRefresh
        );
    }

    public RefreshToken get(String token) {
        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(TokenNotFoundException::new);
    }
}
