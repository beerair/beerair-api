package com.beerair.core.member.facade;


import com.beerair.core.member.application.LevelService;
import com.beerair.core.member.application.MemberService;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LevelResponse;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.dto.request.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberSignFacade {
    private final MemberService memberService;
    private final LevelService levelService;

    public void sign(LoggedInUser user, MemberSignRequest request) {
        Member member = memberService.get(user);
        LevelResponse level = levelService.getIdByExp(0);
        var levelId = level.getId();

        member.sign(request.getNickname(), levelId);
    }
}
