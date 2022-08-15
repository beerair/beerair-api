package com.beerair.core.acceptance.auth;

import com.beerair.core.auth.dto.request.RefreshTokenRequest;
import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

@ScenarioScope
public class AuthStepWhenDefs {
    @Autowired
    private AuthStepClient authStepClient;

    @Given("{string} Refresh Token 으로 Access Token 발급을 요청하면")
    public void registerRefreshToken(String refreshToken) {
        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken);

        authStepClient.issueAccessToken(request);
    }
}
