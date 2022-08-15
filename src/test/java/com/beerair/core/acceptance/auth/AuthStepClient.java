package com.beerair.core.acceptance.auth;

import static com.beerair.core.acceptance.config.RestTemplateFactory.createRestTemplate;
import static io.cucumber.spring.CucumberTestContext.*;

import com.beerair.core.auth.dto.request.RefreshTokenRequest;
import com.beerair.core.common.util.MapperUtil;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.acceptance.member.MemberStepDefs;
import com.beerair.core.common.dto.ResponseDto;

import java.io.IOException;
import java.nio.charset.Charset;

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
}
