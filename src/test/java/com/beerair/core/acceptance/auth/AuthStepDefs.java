package com.beerair.core.acceptance.auth;

import org.springframework.beans.factory.annotation.Autowired;

import com.beerair.core.acceptance.member.MemberStepClient;
import com.beerair.core.auth.application.dto.OAuth2TokenRequest;

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
}
