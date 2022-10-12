package com.beerair.core.batch.job;

import com.beerair.core.cruiser.domain.CruiserClient;
import com.beerair.core.cruiser.dto.request.StaticsSlackCruiserRequest;
import com.beerair.core.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StaticsDailyJob {
    private final CruiserClient cruiserClient;
    private final MemberService memberService;

    public void dailyJob() {
        var memberCount = memberService.count();

        var message = new StaticsSlackCruiserRequest(memberCount).message();

        cruiserClient.send(message);
    }
}
