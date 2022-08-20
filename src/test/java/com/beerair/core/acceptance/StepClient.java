package com.beerair.core.acceptance;

import com.beerair.core.acceptance.auth.AccessTokenHolder;
import com.beerair.core.common.dto.ResponseDto;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.beerair.core.auth.presentation.AuthTokenAuthenticationFilter.TOKEN_TYPE;

public abstract class StepClient {
    private static final String SERVER_URL = "http://localhost";

    @LocalServerPort
    private int port;

    protected final RestTemplate restTemplate;
    private final String endpoint;

    public StepClient(String endpoint) {
        this.restTemplate = new RestTemplateBuilder()
                .errorHandler(new NothingErrorHandler())
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
        this.endpoint = endpoint;
    }

    protected String endpoint() {
        return SERVER_URL + ":" + port + endpoint;
    }

    public <T> void exchange(HttpMethod httpMethod, String path, HttpEntity<T> httpEntity) {
        String url = endpoint() + path;
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

    private static class NothingErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {

        }
    }
}
