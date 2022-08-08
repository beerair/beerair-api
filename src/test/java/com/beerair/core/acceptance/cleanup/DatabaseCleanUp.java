package com.beerair.core.acceptance.cleanup;

import org.springframework.stereotype.Component;

import com.beerair.core.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DatabaseCleanUp implements CleanUp {
    private final MemberRepository memberRepository;

    public void exec() {
        memberRepository.deleteAll();
    }
}
