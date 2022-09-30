package com.beerair.core.member.application;

import com.beerair.core.error.exception.member.MemberNicknameAlreadyExistsException;
import com.beerair.core.error.exception.member.MemberNotFoundException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.dto.response.MemberResponse;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member get(String memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);
    }

    public Member get(LoggedInMember user) {
        return get(user.getId());
    }

    public void resign(LoggedInMember user) {
        Member member = get(user);

        member.resign();
        // TODO Domain Event
    }

    public void changeNickname(LoggedInMember user, String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new MemberNicknameAlreadyExistsException();
        }
        get(user).changeNickname(nickname);
    }

    public MemberResponse getMe(LoggedInMember user) {
        var result = memberRepository.findByIdWithLevel(user.getId())
            .orElseThrow(MemberNotFoundException::new);
        return MemberResponse.from(result);
    }
}
