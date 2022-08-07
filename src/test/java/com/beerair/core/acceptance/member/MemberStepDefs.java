package com.beerair.core.acceptance.member;

import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class MemberStepDefs {
    @Autowired
    private MemberHttpClient memberHttpClient;

    @Given("{string} 이메일로 회원가입이 되어있다.")
    public void sign(String email) {
        var status = memberHttpClient.sign();
        assertThat(status)
            .isEqualTo(HttpStatus.CREATED);
    }

    @Given("{string} 이메일로 로그인 되어있다.")
    public void login(String email) {
        var status = memberHttpClient.login();
        assertThat(status)
            .isEqualTo(HttpStatus.OK);
    }
}
