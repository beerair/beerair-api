package com.beerair.core.acceptance.member;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class MemberStepDefs {
    @When("{string} 소셜 로그인을 요청 하면")
    public void login(String testUserSpec) {
        assert(false);
    }

    @When("사용자 정보 등록을 요청하면")
    public void sign() {
        assert(false);
    }

    @Then("반환된 권한에 {string}가 포함된다.")
    public void containsRole(String role) {
        assert(false);
    }

    @Then("반환된 권한에 {string}가 포함되지 않는다.")
    public void notContainsRole(String role) {
        assert(false);
    }
}
