package com.beerair.core.acceptance.cleanup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.beerair.core.member.infrastructure.MemberRepository;

@Component
public class DatabaseCleanUp implements CleanUp {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void exec() {
        memberRepository.deleteAll();
    }
}
