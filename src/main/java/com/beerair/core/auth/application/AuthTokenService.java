package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.TokenPurpose;
import com.beerair.core.auth.dto.response.TokenRefreshResponse;
import com.beerair.core.cache.authtoken.AuthTokenRedisCacheService;
import com.beerair.core.cache.authtoken.AuthTokenRedisKey;
import com.beerair.core.error.exception.auth.InvalidAuthTokenException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthTokenService {
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final AuthTokenRedisCacheService authTokenRedisCacheService;
    private final RedisTemplate<String, Object> redisTemplate;

    public AuthTokenService(
            RedisTemplate<String, Object> redisTemplate,
            AuthTokenRedisCacheService authTokenRedisCacheService,
            @Qualifier(TokenPurpose.ACCESS) AuthTokenCrypto accessTokenCrypto,
            @Qualifier(TokenPurpose.REFRESH) AuthTokenCrypto refreshTokenCrypto
    ) {
        this.redisTemplate = redisTemplate;
        this.authTokenRedisCacheService = authTokenRedisCacheService;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
    }

    public TokenRefreshResponse issueByRefreshToken(String refreshToken) {
        var authentication = refreshTokenCrypto.decrypt(refreshToken);
        var memberId = authentication.getPrincipal().getId();

        var isRefreshToken = !authTokenRedisCacheService.existsRefreshToken(
                AuthTokenRedisKey.AUTH_REFRESH_TOKEN,
                memberId,
                refreshToken
        );

        if (isRefreshToken) {
            throw new InvalidAuthTokenException();
        }

        var newAccess = accessTokenCrypto.encrypt(authentication);
        var newRefresh = refreshTokenCrypto.encrypt(authentication);
        issueRefreshToken(memberId, newRefresh);
        return new TokenRefreshResponse(
                newAccess, newRefresh
        );
    }

    public void issueRefreshToken(String memberId, AuthToken authToken) {
        authTokenRedisCacheService.issueRefreshToken(
                AuthTokenRedisKey.AUTH_REFRESH_TOKEN,
                memberId,
                authToken
        );
    }

    public void deleteRefreshTokenByMember(String memberId) {
        authTokenRedisCacheService.delete(
                AuthTokenRedisKey.AUTH_REFRESH_TOKEN,
                memberId
        );
    }
}
