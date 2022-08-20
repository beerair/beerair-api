package com.beerair.core.acceptance.cleanup;

import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beerair.core.member.infrastructure.MemberRepository;

@Component
public class DatabaseCleanUp implements CleanUp {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private MemberRepository memberRepository;

    public void exec() {
        refreshTokenRepository.deleteAll();
        memberRepository.deleteAll();
    }
}
