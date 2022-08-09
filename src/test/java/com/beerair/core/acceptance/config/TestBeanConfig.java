package com.beerair.core.acceptance.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.beerair.core.acceptance.auth.FakeAuthenticationConverter;

@TestConfiguration
public class TestBeanConfig {
    @Bean
    public FakeAuthenticationConverter acceptanceAuthenticationConverter() {
        return new FakeAuthenticationConverter();
    }
}
