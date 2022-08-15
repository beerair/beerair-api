package com.beerair.core.acceptance.auth;

import static io.cucumber.spring.CucumberTestContext.*;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.acceptance.member.MemberStepDefs;
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

    public void loginByOAuth2(String registration) {
        var response = this.restTemplate.getForEntity(
            authEndpoint() + "/login/oauth2/code" + registration, Void.class
        );
        CucumberHttpResponseContext.set(response);
    }

    public void issueAccessToken() {
        var response = this.restTemplate.postForEntity(
            authEndpoint() + "/refresh", null, ResponseDto.class
        );
        CucumberHttpResponseContext.set(response);
    }
}
