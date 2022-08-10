package com.beerair.core.acceptance.common;

import java.util.Random;

import com.beerair.core.auth.application.dto.request.OAuth2TokenRequest;

import io.cucumber.java.ParameterType;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class CucumberParameterTypeDefs {
    @ParameterType("\\{소셜:'([A-Z]+)',토큰:'([a-zA-Z0-9]+)'\\}")
    public OAuth2TokenRequest token(String socialType, String token) {
        // TODO Random
        return new OAuth2TokenRequest(socialType, token, new Random().nextInt() + "");
    }
}
