package com.beerair.core.acceptance.member;

import org.springframework.beans.factory.annotation.Autowired;

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
}
