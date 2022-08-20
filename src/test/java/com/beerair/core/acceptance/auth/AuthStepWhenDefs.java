package com.beerair.core.acceptance.auth;

import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

@ScenarioScope
public class AuthStepWhenDefs {
    @Autowired
    private AuthStepClient authStepClient;

    @When("{string} Refresh Token 으로 Access Token 발급을 요청하면")
    public void registerRefreshToken(String refreshToken) {
        authStepClient.issueAccessToken(refreshToken);
    }

    @When("나의 토큰 정보 조회를 요청 하면")
    public void 토큰_정보_조회_요청() {
        authStepClient.getAuthMe();
    }
}
