package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.application.BeerService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerSearchResponse {
    @ApiModelProperty(
        dataType = "Array",
        value = "검색된 맥주"
    )
    private List<BeerResponse> values;

    @ApiModelProperty(
        dataType = "Number",
        value = "검색된 맥주의 갯수",
        example = BeerService.SEARCH_LIMIT + ""
    )
    private int size;

    @ApiModelProperty(
        dataType = "Number",
        value = "모든 맥주의 총 갯수",
        example = "250"
    )
    private long total;

    public static BeerSearchResponse from(List<BeerResponse> beers, long total) {
        return new BeerSearchResponse(beers, beers.size(), total);
    }
}
