package com.beerair.core.cucumber.cleanup;

import com.beerair.core.member.infrastructure.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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
