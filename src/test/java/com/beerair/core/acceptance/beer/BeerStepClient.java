package com.beerair.core.acceptance.beer;

import com.beerair.core.acceptance.StepClient;
import com.beerair.core.beer.dto.request.BeerSearchRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class BeerStepClient extends StepClient {
    public BeerStepClient() {
        super("/api/v1/beer-likes");
    }

    public void search(BeerSearchRequest request) {
        var uriBuilder = UriComponentsBuilder.fromUriString(endpoint());
        if (Objects.nonNull(request.getCountry())) {
            uriBuilder.queryParam("keyword", request.getKeyword());
        }
        if (Objects.nonNull(request.getCountry())) {
            uriBuilder.queryParam("country", request.getCountry());
        }
        if (Objects.nonNull(request.getCountry())) {
            uriBuilder.queryParam("type", request.getType());
        }
        if (Objects.nonNull(request.getOrder())) {
            uriBuilder.queryParam("order", request.getOrder());
        }
        exchange(HttpMethod.GET, uriBuilder.toUriString(), new HttpEntity<>(authed()));
    }
}
