package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.dto.response.TokenResponse;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.error.exception.auth.RefreshTokenNotFoundException;
import com.beerair.core.member.dto.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RefreshTokenProvider {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthTokenEncoder authTokenEncoder;

    public void create(String token) {
        refreshTokenRepository.save(new RefreshToken(token));
    }

    public TokenResponse issueToken(String token) {
        get(token).use();

        LoggedInUser loggedInUser = authTokenEncoder.getLoggedInUser(token);
        var authorities = authTokenEncoder.getAuthorities(token);

        var newAccess = authTokenEncoder.encode(TokenType.ACCESS, loggedInUser, authorities);
        var newRefresh = authTokenEncoder.encode(TokenType.REFRESH, loggedInUser, authorities);
        return new TokenResponse(
                newAccess, newRefresh, authTokenEncoder.getExpired(newAccess)
        );
    }

    public RefreshToken get(String token) {
        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(RefreshTokenNotFoundException::new);
    }
}
