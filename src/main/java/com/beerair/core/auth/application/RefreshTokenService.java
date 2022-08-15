package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthTokenProvider;
import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.dto.response.TokenResponse;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.error.exception.auth.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthTokenProvider authTokenProvider;

    public void create(String token) {
        refreshTokenRepository.save(new RefreshToken(token));
    }

    public TokenResponse issueAccessToken(String token) {
        get(token).use();

        var id = authTokenProvider.getId(token);
        var authorities = authTokenProvider.getAuthorities(token);

        var newAccess = authTokenProvider.encode(TokenType.ACCESS, id, authorities);
        var newRefresh = authTokenProvider.encode(TokenType.REFRESH, id, authorities);
        return new TokenResponse(
                newAccess, newRefresh, authTokenProvider.getExpired(newAccess)
        );
    }

    public RefreshToken get(String token) {
        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(RefreshTokenNotFoundException::new);
    }
}
