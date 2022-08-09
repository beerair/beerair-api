package com.beerair.core.acceptance.member;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.beerair.core.acceptance.auth.OAuth2TokenRegistry;
import com.beerair.core.auth.application.dto.OAuth2TokenRequest;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class MemberStepDefs {
    @Autowired
    private MemberStepClient memberStepClient;

    @When("사용자 정보 등록을 요청하면")
    public void sign() {
        assert(false);
    }

    @Then("{} 권한 {string}가 포함된다.")
    public void containsRole(String role) {
        assert(false);
    }
}
