package com.beerair.core.cucumber.member;

import com.beerair.core.cucumber.StepClient;
import com.beerair.core.member.dto.request.MemberChangeNicknameRequest;
import com.beerair.core.member.dto.request.MemberSignRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class MemberStepClient extends StepClient {
    public MemberStepClient() {
        super("/api/v1/members");
    }

    public void sign(MemberSignRequest request) {
        this.exchange(
                HttpMethod.POST,
                "",
                new HttpEntity<>(request, authed())
        );
    }

    public void resign() {
        this.exchange(
                HttpMethod.DELETE,
                "",
                new HttpEntity<>(authed())
        );
    }

    public void getMemberMe() {
        this.exchange(
                HttpMethod.GET,
                "/me",
                new HttpEntity<>(authed())
        );
    }

    public void changeNickname(MemberChangeNicknameRequest request) {
        this.exchange(
                HttpMethod.PATCH,
                "/nickname",
                new HttpEntity<>(request, authed())
        );
    }
}
