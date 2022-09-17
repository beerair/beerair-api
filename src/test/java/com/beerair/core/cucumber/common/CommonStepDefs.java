package com.beerair.core.cucumber.common;

import com.beerair.core.cucumber.CucumberHttpResponseContext;
import com.beerair.core.common.dto.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
public class CommonStepDefs {
    @Then("요청이 성공한다.")
    public void success() {
        assertThat(CucumberHttpResponseContext.is2XX()).isTrue();
    }

    @Then("요청이 실패한다.")
    public void fail() {
        assertThat(CucumberHttpResponseContext.is3XX() || CucumberHttpResponseContext.is4XX()).isTrue();
    }

    @Then("{string}가 반환된다.")
    public void matchResponseText(String expect) {
        ResponseDto<String> response = CucumberHttpResponseContext.getBody(
                new TypeReference<>() {}
        );
        assertThat(response.getData()).isEqualTo(expect);
    }

    @Then("{int}개가 조회된다.")
    public void matchCollectionSize(int expect) {
        ResponseDto<List<?>> response = CucumberHttpResponseContext.getBody(
                new TypeReference<>() {}
        );
        assertThat(response.getData().size()).isEqualTo(expect);
    }
}
