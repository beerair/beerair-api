package com.beerair.core.actionlog.infrastructure;

import com.beerair.core.actionlog.domain.SystemActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public interface SystemActionLogRepository extends JpaRepository<SystemActionLog, Long> {
    @Transactional(readOnly = true)
    Long countAllByCreatedAtBetween(LocalDateTime startAt, LocalDateTime endAt);
}
