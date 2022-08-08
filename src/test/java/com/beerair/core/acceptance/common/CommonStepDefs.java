package com.beerair.core.acceptance.common;

import static org.assertj.core.api.Assertions.*;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.fasterxml.jackson.core.type.TypeReference;

import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class CommonStepDefs {
    @Then("요청이 성공한다.")
    public void sign() {
        assertThat(CucumberHttpResponseContext.is2XX()).isTrue();
    }

    @Then("{string}가 반환된다.")
    public void matchResponseText(String expect) {
        String response = CucumberHttpResponseContext.getBody(
            new TypeReference<>() {}
        );
        assertThat(response).isEqualTo(expect);
    }
}
