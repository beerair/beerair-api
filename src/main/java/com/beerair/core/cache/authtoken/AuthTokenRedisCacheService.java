package com.beerair.core.cache.authtoken;

import com.beerair.core.auth.domain.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthTokenRedisCacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void delete(AuthTokenRedisKey keyPrefix, String memberId) {
        var cacheKey = keyPrefix.cacheKey(memberId);
        redisTemplate.delete(cacheKey);
    }

    public boolean existsRefreshToken(
            AuthTokenRedisKey keyPrefix,
            String memberId,
            String token
    ) {
        var cacheKey = keyPrefix.cacheKey(memberId);

        var saved = (String) redisTemplate.opsForValue().get(cacheKey);
        return Objects.nonNull(saved) && saved.equals(token);
    }

    public void issueRefreshToken(
            AuthTokenRedisKey keyPrefix,
            String memberId,
            AuthToken authToken
    ) {
        var cacheKey = keyPrefix.cacheKey(memberId);

        redisTemplate.opsForValue().set(
                cacheKey,
                authToken.getToken(),
                (authToken.getExpired().getTime() - new Date().getTime()),
                TimeUnit.MILLISECONDS
        );
    }
}
