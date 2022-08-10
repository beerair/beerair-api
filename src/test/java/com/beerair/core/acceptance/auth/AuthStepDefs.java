package com.beerair.core.acceptance.auth;

import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.auth.application.dto.request.OAuth2TokenRequest;
import com.beerair.core.auth.application.dto.response.AuthResponse;
import com.fasterxml.jackson.core.type.TypeReference;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class AuthStepDefs {
    @Autowired
    private AuthStepClient authStepClient;

    @Given("{token}으로 {string} 이메일이 등록 되어 있다.")
    public void registerOAuth2Token(OAuth2TokenRequest token, String email) {
        OAuth2TokenRegistry.register(token, email);
    }

    @When("{token}으로 소셜 로그인을 요청하면")
    public void login(OAuth2TokenRequest token) {
        authStepClient.login(token);
    }

    @Then("{} 권한 {string}가 포함된다.")
    public void containsRole(String role) {
        AuthResponse authResponse = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        assertThat(authResponse.getRoles())
            .contains(role);
    }
}
