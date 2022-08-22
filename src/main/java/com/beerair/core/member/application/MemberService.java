package com.beerair.core.member.application;

import com.beerair.core.error.exception.member.MemberNotFoundException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.facade.MemberSignFacade;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.dto.request.MemberSignRequest;
import com.beerair.core.member.dto.response.MemberMeResponse;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member get(LoggedInUser user) {
        return memberRepository.findById(user.getId())
                .orElseThrow(MemberNotFoundException::new);
    }

    public void resign(LoggedInUser user) {
        Member member = get(user);

        member.resign();
        // TODO Domain Event
    }

    public void changeNickname(LoggedInUser user, String nickname) {
        get(user).changeNickname(nickname);
    }

    public MemberMeResponse getMe(LoggedInUser user) {
        return memberRepository.findByIdWithLevel(user.getId())
                .orElseThrow(MemberNotFoundException::new);
    }
}
