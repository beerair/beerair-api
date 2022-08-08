package com.beerair.core.acceptance.member;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class MemberStepDefs {
    @Autowired
    private MemberStepClient memberHttpClient;

    @Given("{string} 이메일로 회원가입 했을때")
    public void sign(String email) {
        memberHttpClient.sign();
    }
}
