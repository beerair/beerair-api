package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.TokenPurpose;
import com.beerair.core.auth.dto.response.TokenRefreshResponse;
import com.beerair.core.error.exception.auth.InvalidAuthTokenException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class AuthTokenService {
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final RedisTemplate<String, Object> redisTemplate;

    public AuthTokenService(
            RedisTemplate<String, Object> redisTemplate,
            @Qualifier(TokenPurpose.ACCESS) AuthTokenCrypto accessTokenCrypto,
            @Qualifier(TokenPurpose.REFRESH) AuthTokenCrypto refreshTokenCrypto
    ) {
        this.redisTemplate = redisTemplate;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
    }

    public TokenRefreshResponse issueByRefreshToken(String refreshToken) {
        var authentication = refreshTokenCrypto.decrypt(refreshToken);
        var memberId = authentication.getPrincipal().getId();
        if (!existsRefreshToken(memberId, refreshToken)) {
            throw new InvalidAuthTokenException();
        }

        var newAccess = accessTokenCrypto.encrypt(authentication);
        var newRefresh = refreshTokenCrypto.encrypt(authentication);
        issueRefreshToken(memberId, newRefresh);
        return new TokenRefreshResponse(
                newAccess, newRefresh
        );
    }

    private boolean existsRefreshToken(String memberId, String token) {
        String saved = (String) redisTemplate.opsForValue().get(refreshTokenKey(memberId));
        return Objects.nonNull(saved) && saved.equals(token);
    }

    private String refreshTokenKey(String memberId) {
        return "refreshToken:" + memberId;
    }

    public void issueRefreshToken(String memberId, AuthToken authToken) {
        redisTemplate.opsForValue().set(
                refreshTokenKey(memberId),
                authToken.getToken(),
                (authToken.getExpired().getTime() - new Date().getTime()),
                TimeUnit.MILLISECONDS
        );
    }

    public void deleteRefreshTokenByMember(String memberId) {
        redisTemplate.delete(refreshTokenKey(memberId));
    }
}
