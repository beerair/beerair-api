package com.beerair.core.acceptance.beer;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.acceptance.StepClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class BeerLikeStepClient extends StepClient {
    public BeerLikeStepClient() {
        super("/api/v1/beer-likes");
    }

    public void toggle(String beerId) {
        String query = "?beerId=" + beerId;
        exchange(HttpMethod.POST, query, new HttpEntity<>(authed()));
    }

    public void getCount() {
        exchange(HttpMethod.GET, "/count", new HttpEntity<>(authed()));
    }

    public void getAll() {
        exchange(HttpMethod.GET, "", new HttpEntity<>(authed()));
    }
}
