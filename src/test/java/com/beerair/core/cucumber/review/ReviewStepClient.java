package com.beerair.core.cucumber.review;

import com.beerair.core.cucumber.StepClient;
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

    public void getAllByBeer(String beerId) {
        exchange(HttpMethod.GET, "?beerId=" + beerId, new HttpEntity<>(authed()));
    }

    public void getAllByMe() {
        exchange(HttpMethod.GET, "/me", new HttpEntity<>(authed()));
    }

    public void getLatestByMe() {
        exchange(HttpMethod.GET, "/me/latest", new HttpEntity<>(authed()));
    }

    public void get(String beerId) {
        exchange(HttpMethod.GET, "/" + beerId, new HttpEntity<>(authed()));
    }

    public void create(ReviewRequest request) {
        var httpEntity = new HttpEntity<>(request, authed());
        exchange(HttpMethod.POST, "", httpEntity);
    }

    public void delete(String beerId) {
        exchange(HttpMethod.DELETE, "/" + beerId, new HttpEntity<>(authed()));
    }
}
