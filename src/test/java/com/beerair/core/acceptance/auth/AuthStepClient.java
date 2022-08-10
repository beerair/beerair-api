package com.beerair.core.acceptance.auth;

import static io.cucumber.spring.CucumberTestContext.*;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.auth.application.dto.request.OAuth2TokenRequest;
import com.beerair.core.common.dto.ResponseDto;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class AuthStepClient {
    private static final String SERVER_URL = "http://localhost";
    private static final String ENDPOINT = "/api/v1/auths";

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate;

    public AuthStepClient() {
        this.restTemplate = new RestTemplate();
    }

    private String authEndpoint() {
        return SERVER_URL + ":" + port + ENDPOINT;
    }

    public void login(OAuth2TokenRequest request) {
        final String url = authEndpoint() + "/oauth2";
        var response = restTemplate.postForEntity(
            url, request, ResponseDto.class
        );
        CucumberHttpResponseContext.set(response);
    }
}
