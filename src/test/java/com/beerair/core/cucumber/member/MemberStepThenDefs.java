package com.beerair.core.cucumber.member;

import com.beerair.core.cucumber.CucumberHttpResponseContext;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.response.MemberResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
public class MemberStepThenDefs {
    @Then("나의 정보에서 티어는 {int} 이다.")
    public void 레벨_정보_검증(int expertLevel) {
        ResponseDto<MemberResponse> response =
                CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        assertThat(response.getData().getTier())
                .isEqualTo(expertLevel);
    }

    @Then("나의 정보에서 권한 갯수는 {int}개 이다.")
    public void 권한_갯수_검증(int size) {
        ResponseDto<MemberResponse> response =
            CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        assertThat(response.getData().getAuthorities().size())
            .isEqualTo(size);
    }
}
