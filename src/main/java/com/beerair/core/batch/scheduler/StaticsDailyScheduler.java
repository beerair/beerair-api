package com.beerair.core.batch.scheduler;

import com.beerair.core.batch.job.StaticsDailyJob;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StaticsDailyScheduler {
    private final StaticsDailyJob staticsDailyJob;

    @Scheduled(cron = "59 59 23 * * *")
    public void dailyJob() {
        staticsDailyJob.dailyJob();
    }
}
