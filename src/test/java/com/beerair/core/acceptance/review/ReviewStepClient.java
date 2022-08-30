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
public class ReviewStepClient extends StepClient {
    public ReviewStepClient() {
        super("/api/v1/reviews");
    }

    public void get(String beerId) {
        String query = "?beerId=" + beerId;
        exchange(HttpMethod.GET, query, new HttpEntity<>(authed()));
    }

    public void create(ReviewRequest request) {
        var httpEntity = new HttpEntity<>(request, authed());
        exchange(HttpMethod.POST, "", httpEntity);
    }

    public void delete(String beerId) {
        String query = "?beerId=" + beerId;
        exchange(HttpMethod.DELETE, query, new HttpEntity<>(authed()));
    }
}
