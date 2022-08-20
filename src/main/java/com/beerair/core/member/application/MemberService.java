package com.beerair.core.member.application;

import com.beerair.core.error.exception.member.MemberNotFoundException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.dto.request.MemberSignRequest;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private Member get(LoggedInUser user) {
        return memberRepository.findById(user.getId())
                .orElseThrow(MemberNotFoundException::new);
    }

    public void sign(LoggedInUser user, MemberSignRequest request) {
        get(user).sign(request.getNickname());
    }

    public void resign(LoggedInUser user) {
        get(user).resign();
    }

    public void changeNickname(LoggedInUser user, String nickname) {
        get(user).changeNickname(nickname);
    }
}
