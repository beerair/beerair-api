package com.beerair.core.member.application.eventlistener;

import com.beerair.core.event.ReviewCreateEventArgs;
import com.beerair.core.member.application.LevelService;
import com.beerair.core.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberReviewEventListener {
    private final MemberService memberService;
    private final LevelService levelService;

    @EventListener
    public void onCreateReview(ReviewCreateEventArgs args) {
        var levels = levelService.getLevels();
        var member = memberService.get(args.getMemberId());
        member.increaseExp(levels);
    }
}
