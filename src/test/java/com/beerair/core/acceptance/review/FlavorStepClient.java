package com.beerair.core.acceptance.review;

import com.beerair.core.acceptance.StepClient;
import com.beerair.core.review.dto.request.ReviewRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class FlavorStepClient extends StepClient {
    public FlavorStepClient() {
        super("/api/v1/flavors");
    }

    public void getFlavorTop3(String beerId) {
        String url = "/rank?beerId=" + beerId;
        exchange(HttpMethod.GET, url, new HttpEntity<>(authed()));
    }
}
