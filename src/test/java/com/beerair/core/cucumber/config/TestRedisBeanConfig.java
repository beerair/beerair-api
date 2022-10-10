package com.beerair.core.cucumber.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

@Slf4j
@Profile("test")
@Configuration
public class TestRedisBeanConfig {
    private RedisServer redisServer;

    public TestRedisBeanConfig(@Value("${spring.redis.port}") int redisPort) {
        redisServer = new RedisServer(redisPort);
    }

    @PostConstruct
    public void startRedis() {
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }
}
