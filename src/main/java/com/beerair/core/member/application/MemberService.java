package com.beerair.core.member.application;

import java.util.Optional;

import com.beerair.core.member.application.dto.response.MemberResponse;
import com.beerair.core.member.application.dto.response.OAuth2Member;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> getMember(String email) {
        return Optional.empty();
    }
}
