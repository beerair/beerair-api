package com.beerair.core.beer.dto.request;

import com.beerair.core.beer.infrastructure.BeerOrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BeerSearchRequest {
    @Schema(description = "검색 키워드 (맥주 이름)")
    private String keyword;

    @Schema(description = "나라 ID", example = "1")
    private List<Long> country;

    @Schema(description = "맥주 종류 ID", example = "1")
    private List<Long> type;

    @Schema(description = "정렬", defaultValue = "REVIEW")
    private BeerOrderBy order;

    @Schema(description = "offset", defaultValue = "0")
    private int offset;
}
