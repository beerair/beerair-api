package com.beerair.core.cucumber.beer;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import com.beerair.core.cucumber.StepClient;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class BeerLikeStepClient extends StepClient {
    public BeerLikeStepClient() {
        super("/api/v1/beer-likes");
    }

    public void like(Integer beerId) {
        String query = "?beerId=" + beerId;
        exchange(HttpMethod.POST, query, new HttpEntity<>(authed()));
    }

    public void unlike(Integer beerId) {
        String query = "?beerId=" + beerId;
        exchange(HttpMethod.DELETE, query, new HttpEntity<>(authed()));
    }

    public void getCount() {
        exchange(HttpMethod.GET, "/count", new HttpEntity<>(authed()));
    }

    public void getAll() {
        exchange(HttpMethod.GET, "", new HttpEntity<>(authed()));
    }
}
