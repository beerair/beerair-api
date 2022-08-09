package com.beerair.core.acceptance.member;

import static io.cucumber.spring.CucumberTestContext.*;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.beerair.core.acceptance.CucumberHttpResponseContext;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class MemberStepClient {
    private static final String SERVER_URL = "http://localhost";
    private static final String ENDPOINT = "/api/v1/members";

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate;

    public MemberStepClient() {
        this.restTemplate = new RestTemplate();
    }

    private String memberEndpoint() {
        return SERVER_URL + ":" + port + ENDPOINT;
    }
}
