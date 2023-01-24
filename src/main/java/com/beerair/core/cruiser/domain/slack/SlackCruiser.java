package com.beerair.core.cruiser.domain.slack;

import com.beerair.core.config.cruiser.CruiserProperties;
import com.beerair.core.cruiser.domain.CruiserClient;
import com.beerair.core.cruiser.dto.slack.CruiserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SlackCruiser implements CruiserClient {
    private final CruiserProperties cruiserProperties;
    private final WebClient.Builder webclient;
    private final Environment environment;

    @Override
    public void send(CruiserRequest request) {
        webclient.build()
                .post()
                .uri(cruiserProperties.getCruiser())
                .body(Mono.just(request), CruiserRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }
}
