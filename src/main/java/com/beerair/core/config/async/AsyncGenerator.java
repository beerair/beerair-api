package com.beerair.core.config.async;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class AsyncGenerator {
    public ThreadPoolTaskExecutor executor(String threadName, int corePoolSize, int maxPoolSize, int queueCapacity) {
        var executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadName + "-");
        executor.initialize();

        return executor;
    }
}
