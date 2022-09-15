package com.beerair.core.auth.application;

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

@Transactional
@Service
public class AuthTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final RedisTemplate<String, Object> redisTemplate;

    public AuthTokenService(
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

    public void issue(String memberId, String token) {
        deleteByMember(memberId);
        refreshTokenRepository.save(new RefreshToken(memberId, token));
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

    public void deleteByMember(String memberId) {
        refreshTokenRepository.findAllByMemberId(memberId)
                .forEach(RefreshToken::delete);
        redisTemplate.delete("authToken:" + memberId);
    }

    public RefreshToken get(String token) {
        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(TokenNotFoundException::new);
    }
}
