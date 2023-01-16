package com.beerair.core.actionlog.application;

import com.beerair.core.actionlog.domain.SystemActionLog;
import com.beerair.core.actionlog.infrastructure.SystemActionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SystemActionLogService {
    private final SystemActionLogRepository systemActionLogRepository;

    public void insert(SystemActionLog systemActionLog) {
        systemActionLogRepository.save(systemActionLog);
    }

    public Long count() {
        var now = LocalDateTime.now();
        var yesterday = now.minusDays(1);

        return systemActionLogRepository.countAllByCreatedAtBetween(yesterday, now);
    }
}
