package com.beerair.core.config.cruiser;

import com.beerair.core.cruiser.domain.CruiserClient;
import com.beerair.core.cruiser.domain.slack.SlackCruiser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CruiserConfig {
    private final CruiserProperties cruiserProperties;
    private final WebClient.Builder webClient;
    private final Environment environment;

    @Bean
    public CruiserClient slackCruiser() {
        return new SlackCruiser(
                cruiserProperties,
                webClient,
                environment
        );
    }
}
