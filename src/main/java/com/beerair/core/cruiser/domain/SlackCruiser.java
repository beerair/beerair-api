package com.beerair.core.cruiser.domain;

import com.beerair.core.config.cruiser.CruiserProperties;
import com.beerair.core.cruiser.dto.slack.CruiserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RequiredArgsConstructor
public class SlackCruiser implements CruiserClient {
    private final CruiserProperties cruiserProperties;
    private final WebClient.Builder webclient;

    @Override
    public void send(CruiserRequest request) {
        webclient.build()
                .post()
                .uri(cruiserProperties.getCruiser())
                .body(Mono.just(request), CruiserRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000))
                .block();
    }
}
