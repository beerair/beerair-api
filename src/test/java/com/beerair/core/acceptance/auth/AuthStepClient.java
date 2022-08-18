package com.beerair.core.acceptance.auth;

import static com.beerair.core.acceptance.config.RestTemplateFactory.createRestTemplate;
import static com.beerair.core.auth.presentation.AuthTokenAuthenticationFilter.TOKEN_TYPE;
import static io.cucumber.spring.CucumberTestContext.*;

import com.beerair.core.auth.dto.request.RefreshTokenRequest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
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
        this.restTemplate = createRestTemplate();
    }

    private String authEndpoint() {
        return SERVER_URL + ":" + port + ENDPOINT;
    }

    public void issueAccessToken(RefreshTokenRequest request) {
        var response = this.restTemplate.postForEntity(
                authEndpoint() + "/refresh", request, ResponseDto.class
        );
        CucumberHttpResponseContext.set(response);
    }

    public void getAuthMe(String access) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", TOKEN_TYPE + " " + access);

        var response = this.restTemplate.exchange(
                authEndpoint() + "/me",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ResponseDto.class
        );
        CucumberHttpResponseContext.set(response);
    }
}
