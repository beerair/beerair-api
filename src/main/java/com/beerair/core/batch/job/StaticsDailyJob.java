package com.beerair.core.batch.job;

import com.beerair.core.actionlog.application.SystemActionLogService;
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
    private final SystemActionLogService systemActionLogService;

    public void dailyJob() {
        var memberCount = memberService.count();
        var totalApiCallCount = systemActionLogService.count();

        var message = new StaticsSlackCruiserRequest(memberCount, totalApiCallCount).message();

        cruiserClient.send(message);
    }
}
