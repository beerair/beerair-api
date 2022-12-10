package com.beerair.core.actionlog.event.subscribe;

import com.beerair.core.actionlog.application.SystemActionLogService;
import com.beerair.core.actionlog.domain.SystemActionLog;
import com.beerair.core.actionlog.event.model.SystemActionLogEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventSubscribe {
    private final SystemActionLogService systemActionLogService;

    @Async(value = "systemActionLogExecutor")
    @EventListener(SystemActionLogEventModel.class)
    public void subscribe(SystemActionLogEventModel model) {
        var systemActionLog = new SystemActionLog(
                model.getIpAddress(),
                model.getPath(),
                model.getMethod(),
                model.getUserAgent(),
                model.getHost(),
                model.getReferer()
        );

        systemActionLogService.insert(systemActionLog);
    }
}
