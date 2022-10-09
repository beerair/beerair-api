package com.beerair.core.cucumber.beer;

import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.beer.infrastructure.search.BeerOrderBy;
import io.cucumber.java.en.Given;
import java.util.ArrayList;
import java.util.Objects;

public class BeerSearchGivenDefs {
    public static BeerSearchRequest searchRequest;

    @Given("[맥주 검색 조건 초기화]")
    public void 맥주_검색_조건_초기화() {
        searchRequest = new BeerSearchRequest();
    }

    @Given("맥주 검색 조건에 offset {int} 등록")
    public void offset_등록(int offset) {
        searchRequest.setOffset(offset);
    }

    @Given("맥주 검색 조건에 키워드 {string} 등록")
    public void 맥주_키워드_등록(String keyword) {
        searchRequest.setKeyword(keyword);
    }

    @Given("맥주 검색 조건에 국가 ID {int} 추가")
    public void 국가_ID_추가(int country) {
        if (Objects.isNull(searchRequest.getCountry())) {
            searchRequest.setCountry(new ArrayList<>());
        }
        searchRequest.getCountry().add((long) country);
    }

    @Given("맥주 검색 조건에 맥주 종류 ID {int} 추가")
    public void 맥주_종류_ID_추가(int type) {
        if (Objects.isNull(searchRequest.getType())) {
            searchRequest.setType(new ArrayList<>());
        }
        searchRequest.getType().add((long) type);
    }

    @Given("맥주 검색 조건에 정렬 {string} 등록")
    public void 맥주_정렬_등록(String order) {
        searchRequest.setOrder(BeerOrderBy.valueOf(order));
    }
}
