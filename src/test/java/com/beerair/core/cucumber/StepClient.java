package com.beerair.core.cucumber;

import static com.beerair.core.auth.presentation.tokenreader.HeaderAuthTokenReader.TOKEN_TYPE;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.cucumber.auth.AccessTokenHolder;
import java.util.Objects;
import lombok.SneakyThrows;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

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

    public <T> void exchange(HttpMethod httpMethod, String path, HttpEntity<T> httpEntity, Class<?> responseClazz) {
        String url = endpoint() + path;
        var response = this.restTemplate.exchange(
                url, httpMethod, httpEntity, responseClazz
        );
        CucumberHttpResponseContext.set(response);
    }

    public <T> void exchange(HttpMethod httpMethod, String path, HttpEntity<T> httpEntity) {
        exchange(httpMethod, path, httpEntity, ResponseDto.class);
    }

    @SneakyThrows
    protected HttpHeaders authed() {
        HttpHeaders headers = new HttpHeaders();
        if (Objects.nonNull(AccessTokenHolder.access)) {
            var value = TOKEN_TYPE + " " + AccessTokenHolder.access;
            headers.add("authorization", value);
            headers.add("Cookie", "accessToken=" + AccessTokenHolder.access);
        }
        return headers;
    }

    private static class NothingErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) {

        }
    }
}
