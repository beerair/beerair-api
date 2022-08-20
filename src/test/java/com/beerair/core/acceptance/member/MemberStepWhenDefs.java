package com.beerair.core.acceptance.member;

import com.beerair.core.acceptance.auth.AuthStepClient;
import com.beerair.core.common.util.TimeUtil;
import com.beerair.core.member.dto.request.MemberSignRequest;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.*;

@ScenarioScope
public class MemberStepWhenDefs {
    @Autowired
    private MemberStepClient memberStepClient;

    @When("회원가입 요청을 하면")
    public void registerRefreshToken() {
        MemberSignRequest request = new MemberSignRequest(randomAlphabetic(10));
        memberStepClient.sign(request);
    }
}
