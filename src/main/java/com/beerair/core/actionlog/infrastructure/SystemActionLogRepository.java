package com.beerair.core.actionlog.infrastructure;

import com.beerair.core.actionlog.domain.SystemActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SystemActionLogRepository extends JpaRepository<SystemActionLog, Long> {

}
