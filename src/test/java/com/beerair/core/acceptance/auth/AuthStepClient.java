package com.beerair.core.acceptance.auth;

import com.beerair.core.acceptance.StepClient;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class AuthStepClient extends StepClient {
    public AuthStepClient() {
        super("/api/v1/auths");
    }

    public void issueAccessToken(String refresh) {
        this.exchange(
                HttpMethod.POST,
                refresh + "/access-token",
                new HttpEntity<>(authed())
        );
    }

    public void getAuthMe() {
        this.exchange(
                HttpMethod.GET,
                "/me",
                new HttpEntity<>(authed())
        );
    }
}
