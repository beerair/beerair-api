package com.beerair.core.acceptance;

import com.beerair.core.acceptance.auth.AccessTokenHolder;
import com.beerair.core.common.dto.ResponseDto;
import lombok.Builder;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.beerair.core.auth.presentation.AuthTokenAuthenticationFilter.TOKEN_TYPE;


public abstract class StepClient {
    private static final String SERVER_URL = "http://localhost";

    @LocalServerPort
    private int port;

    protected final RestTemplate restTemplate;
    private final String endpoint;

    public StepClient(String endpoint) {
        this.restTemplate = new RestTemplate();
        this.endpoint = endpoint;
    }

    protected String endpoint() {
        return SERVER_URL + ":" + port + endpoint;
    }

    public <T> void exchange(HttpMethod httpMethod, String path, HttpEntity<T> httpEntity) {
        String url = UriComponentsBuilder.fromHttpUrl(endpoint())
                .path(path)
                .build()
                .toUriString();
        var response = this.restTemplate.exchange(
                url, httpMethod, httpEntity, ResponseDto.class
        );
        CucumberHttpResponseContext.set(response);
    }

    protected HttpHeaders authed() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", TOKEN_TYPE + " " + AccessTokenHolder.access);
        return headers;
    }
}
