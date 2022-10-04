package com.beerair.core.beer.dto.request;

import com.beerair.core.beer.infrastructure.search.BeerOrderBy;
import com.beerair.core.beer.infrastructure.search.BeerSearchCondition;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.Data;

@Data
public class BeerSearchRequest {
    @ApiParam(value = "검색 키워드 (맥주 이름)")
    private String keyword;

    @ApiParam(value = "나라 ID", example = "1")
    private List<Long> country;

    @ApiParam(value = "맥주 종류 ID", example = "1")
    private List<Long> type;

    @ApiParam(value = "정렬", defaultValue = "REVIEW")
    private BeerOrderBy order;

    @ApiParam(value = "offset", defaultValue = "0")
    private int offset;

    public BeerSearchCondition toBeerSearchCondition() {
        return BeerSearchCondition.builder()
                .keyword(keyword)
                .countries(country)
                .types(type)
                .build();
    }
}
