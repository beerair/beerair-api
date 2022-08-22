package com.beerair.core.acceptance.cleanup;

import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.beerair.core.member.infrastructure.MemberRepository;

@Component
public class DatabaseCleanUp implements CleanUp {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void exec() {
        refreshTokenRepository.deleteAll();
        memberRepository.deleteAll();
    }
}
