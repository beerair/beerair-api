package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.domain.TokenPurpose;
import com.beerair.core.auth.dto.response.TokenRefreshResponse;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.error.exception.auth.TokenNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final RedisTemplate<String, Object> redisTemplate;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            RedisTemplate<String, Object> redisTemplate,
            @Qualifier(TokenPurpose.ACCESS) AuthTokenCrypto accessTokenCrypto,
            @Qualifier(TokenPurpose.REFRESH) AuthTokenCrypto refreshTokenCrypto
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.redisTemplate = redisTemplate;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
    }

    @Deprecated
    public TokenRefreshResponse issueByRefreshToken(String token) {
        get(token).use();

        var authentication = refreshTokenCrypto.decrypt(token);
        var newAccess = accessTokenCrypto.encrypt(authentication).getToken();
        var newRefresh = refreshTokenCrypto.encrypt(authentication).getToken();
        return new TokenRefreshResponse(
                newAccess, newRefresh
        );
    }

    public void issue(String memberId, AuthToken authToken) {
        redisTemplate.opsForValue().set(
                "refreshToken:" + memberId,
                authToken.getToken(),
                (authToken.getExpired().getTime() - new Date().getTime()),
                TimeUnit.MILLISECONDS
        );
    }

    public void deleteByMember(String memberId) {
        redisTemplate.delete("refreshToken:" + memberId);
    }

    public RefreshToken get(String token) {
        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(TokenNotFoundException::new);
    }
}
