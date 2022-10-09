package com.beerair.core.cucumber.beer;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.cucumber.StepClient;
import java.util.Optional;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class BeerStepClient extends StepClient {
    public BeerStepClient() {
        super("/api/v1/beers");
    }

    public void search(BeerSearchRequest request) {
        var uriBuilder = UriComponentsBuilder.newInstance();
        uriBuilder.queryParamIfPresent(
                "keyword", Optional.ofNullable(request.getKeyword())
        );
        uriBuilder.queryParamIfPresent(
                "country", Optional.ofNullable(request.getCountry())
        );
        uriBuilder.queryParamIfPresent(
                "type", Optional.ofNullable(request.getType())
        );
        uriBuilder.queryParamIfPresent(
                "order", Optional.ofNullable(request.getOrder())
        );
        exchange(HttpMethod.GET, uriBuilder.toUriString(), new HttpEntity<>(authed()));
    }

    public void getRecommends() {
        exchange(HttpMethod.GET, "/recommends", new HttpEntity<>(authed()));
    }
}
