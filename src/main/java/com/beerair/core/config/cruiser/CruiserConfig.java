package com.beerair.core.config.cruiser;

import com.beerair.core.cruiser.domain.CruiserClient;
import com.beerair.core.cruiser.domain.SlackCruiser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CruiserConfig {
    private final CruiserProperties cruiserProperties;
    private final WebClient.Builder webClient;

    @Bean
    @ConditionalOnProperty(prefix = "monitor.slack", name = "cruiser")
    public CruiserClient slackCruiser() {
        return new SlackCruiser(cruiserProperties, webClient);
    }
}
