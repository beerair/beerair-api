package com.beerair.core.cucumber.common;

import static org.assertj.core.api.Assertions.assertThat;

import com.beerair.core.common.dto.CursorPageDto;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.cucumber.CucumberHttpResponseContext;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;
import java.util.List;

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
        List<?> response = CucumberHttpResponseContext.getListInFirstClassBody(
            new TypeReference<>() {}
        );
        assertThat(response.size()).isEqualTo(expect);
    }

    @Given("-- 리스트 중 {int}번째 선택")
    public void select(int index) {
        CucumberHttpResponseContext.selectByList(index);
    }

    @Given("-- Cursor 저장")
    public void saveCursor() {
        CucumberHttpResponseContext.saveCursor();
    }

    @Given("-- Cursor 초기화")
    public void clearCursor() {
        CucumberHttpResponseContext.clearCursor();
    }

    @Then("다음 Cursor를 반환하지 않는다.")
    public void hasNextIsFalse() {
        CursorPageDto<?, ?> response = CucumberHttpResponseContext.getBody(
                new TypeReference<>() {}
        );
        assertThat(response.getHasNext()).isFalse();
    }
}
