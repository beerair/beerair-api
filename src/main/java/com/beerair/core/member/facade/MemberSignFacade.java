package com.beerair.core.member.facade;


import com.beerair.core.member.application.LevelService;
import com.beerair.core.member.application.MemberService;
import com.beerair.core.member.dto.LoggedInMember;
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

    public void sign(LoggedInMember user, MemberSignRequest request) {
        var member = memberService.get(user);
        var levels = levelService.getLevels();
        var level = levels.getByExp(0);

        member.sign(request.getNickname(), level.getId());
    }
}
