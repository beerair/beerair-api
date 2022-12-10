package com.beerair.core.config.async;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class AsyncConfig extends AsyncConfigurerSupport {
    private final AsyncGenerator asyncGenerator;

    @Bean(name = {"systemActionLogExecutor"})
    public ThreadPoolTaskExecutor systemActionLogExecutor() {
        return asyncGenerator.executor("systemActionLogExecutor", 5, 10, 10);
    }
}
