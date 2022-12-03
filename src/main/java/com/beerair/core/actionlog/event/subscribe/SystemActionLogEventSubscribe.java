package com.beerair.core.actionlog.event.subscribe;

import com.beerair.core.actionlog.domain.SystemActionLog;
import com.beerair.core.actionlog.event.model.SystemActionLogEventModel;
import com.beerair.core.actionlog.infrastructure.SystemActionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventSubscribe {
    private final SystemActionLogRepository systemActionLogRepository;

    @EventListener(SystemActionLogEventModel.class)
    public void subscribe(SystemActionLogEventModel model) {
        systemActionLogRepository.save(
                new SystemActionLog(
                        model.getIpAddress(),
                        model.getPath(),
                        model.getMethod(),
                        model.getUserAgent(),
                        model.getHost(),
                        model.getReferer()
                )
        );
    }
}
