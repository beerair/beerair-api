package com.beerair.core.member.application;

import com.beerair.core.error.exception.member.MemberNicknameAlreadyExistsException;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberValidationService {
    private final MemberRepository memberRepository;

    public void verifyNickname(String nickname) {
        System.out.println(memberRepository.existsByNickname(nickname));
        if (memberRepository.existsByNickname(nickname)) {
            throw new MemberNicknameAlreadyExistsException();
        }
    }
}
