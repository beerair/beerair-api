package com.beerair.core.acceptance.beer;

import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
public class BeerSearchThenDefs {
    @Then("맥주 검색 결과의 검색된 갯수는 {int}개 이다.")
    public void 검색_결과_갯수_검증(int expert) {
        assertThat(false).isTrue();
    }

    @Then("맥주 검색 결과의 {int}번 맥주의 이름은 {string} 이다.")
    public void 검색_검증(int index, String name) {
        assertThat(false).isTrue();
    }
}
