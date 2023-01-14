package com.beerair.core.cucumber.beer;

import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.common.dto.PageResponseDto;
import com.beerair.core.cucumber.StepClient;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

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
        exchange(HttpMethod.GET, uriBuilder.toUriString(), new HttpEntity<>(authed()), PageResponseDto.class);
    }

    public void getRecommends() {
        exchange(HttpMethod.GET, "/recommends", new HttpEntity<>(authed()));
    }
}
