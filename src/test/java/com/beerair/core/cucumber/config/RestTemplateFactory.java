package com.beerair.core.cucumber.config;

import java.io.IOException;
import lombok.experimental.UtilityClass;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@UtilityClass
public class RestTemplateFactory {
    public static RestTemplate createRestTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(new ResponseErrorHandler() {
                    @Override
                    public boolean hasError(ClientHttpResponse response) throws IOException {
                        return false;
                    }

                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {
                    }
                })
                .build();
    }
}
