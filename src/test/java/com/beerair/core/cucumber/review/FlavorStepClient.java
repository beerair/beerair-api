package com.beerair.core.cucumber.review;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import com.beerair.core.cucumber.StepClient;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class FlavorStepClient extends StepClient {
    public FlavorStepClient() {
        super("/api/v1/flavors");
    }

    public void getFlavorRank(String beerId, int limit) {
        String url = "/rank?beerId=" + beerId + "&limit=" + limit;
        exchange(HttpMethod.GET, url, new HttpEntity<>(authed()));
    }
}
