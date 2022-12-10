package com.beerair.core.actionlog.application;

import com.beerair.core.actionlog.domain.SystemActionLog;
import com.beerair.core.actionlog.infrastructure.SystemActionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemActionLogService {
    private final SystemActionLogRepository systemActionLogRepository;

    public void insert(SystemActionLog systemActionLog) {
        systemActionLogRepository.save(systemActionLog);
    }
}
