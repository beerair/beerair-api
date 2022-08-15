package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.dto.response.TokenResponse;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.error.exception.auth.RefreshTokenNotFoundException;
import com.beerair.core.member.dto.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthTokenEncoder accessTokenEncoder;
    private final AuthTokenEncoder refreshTokenEncoder;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            @Qualifier(TokenType.ACCESS) AuthTokenEncoder accessTokenEncoder,
            @Qualifier(TokenType.REFRESH) AuthTokenEncoder refreshTokenEncoder
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenEncoder = accessTokenEncoder;
        this.refreshTokenEncoder = refreshTokenEncoder;
    }

    public void create(String token) {
        refreshTokenRepository.save(new RefreshToken(token));
    }

    public TokenResponse issueToken(String token) {
        get(token).use();

        LoggedInUser loggedInUser = refreshTokenEncoder.getLoggedInUser(token);
        var authorities = refreshTokenEncoder.getAuthorities(token);

        var newAccess = accessTokenEncoder.encode(loggedInUser, authorities);
        var newRefresh = refreshTokenEncoder.encode(loggedInUser, authorities);
        return new TokenResponse(
                newAccess, newRefresh, accessTokenEncoder.getExpired(newAccess)
        );
    }

    public RefreshToken get(String token) {
        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(RefreshTokenNotFoundException::new);
    }
}
