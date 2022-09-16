package com.beerair.core.acceptance.member;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.acceptance.auth.AccessTokenHolder;
import com.beerair.core.member.dto.request.MemberChangeNicknameRequest;
import com.beerair.core.member.dto.request.MemberSignRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@ScenarioScope
public class MemberStepWhenDefs {
    @Autowired
    private MemberStepClient memberStepClient;

    @When("회원 가입을 요청 하면")
    public void sign() {
        MemberSignRequest request = new MemberSignRequest(randomAlphabetic(10));
        memberStepClient.sign(request);
    }

    @When("닉네임 변경을 요청 하면")
    public void changeNickname() {
        MemberChangeNicknameRequest request = new MemberChangeNicknameRequest(randomAlphabetic(10));
        memberStepClient.changeNickname(request);
    }

    @When("회원 탈퇴를 요청 하면")
    public void resign() {
        memberStepClient.resign();
    }

    @When("나의 정보를 요청 하면")
    public void 나의_정보_조회_요청() {
        memberStepClient.getMemberMe();
    }
}
