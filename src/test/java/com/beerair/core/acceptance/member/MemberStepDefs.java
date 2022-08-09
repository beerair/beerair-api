package com.beerair.core.acceptance.member;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class MemberStepDefs {
    @Autowired
    private MemberStepClient memberHttpClient;

    @Given("{string} 소셜 계정으로 로그인을 요청하면")
    public void login(String testUserSpec) {
    }

    @When("사용자 정보 등록을 요청 하면")
    public void sign(String testUsername) {
    }

    @Then("반환된 권한에 {string} 권한이 포함된다.")
    public void containsRole() {
    }
}
