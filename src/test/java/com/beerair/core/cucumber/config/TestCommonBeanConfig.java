package com.beerair.core.cucumber.config;

import com.beerair.core.common.domain.AES256Crypto;
import com.beerair.core.common.domain.Crypto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestCommonBeanConfig {
    @Bean
    public Crypto crypto() {
        return new AES256Crypto("fHIIZvHYtHAHnSSCOwo4BUqX0inGngql");
    }
}
