package com.beerair.core.review.dto.response;

import com.beerair.core.review.dto.query.FlavorRankDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FlavorRankResponse {
    @ApiModelProperty(
        dataType = "Number",
        value = "맛 ID",
        example = "1"
    )
    private Long id;

    @ApiModelProperty(
        dataType = "String",
        value = "맛 내용",
        example = "단 맛이나요"
    )
    private String content;

    @ApiModelProperty(
        dataType = "Number",
        value = "집계된 리뷰 갯수",
        example = "200"
    )
    private Long count;

    public static FlavorRankResponse from(FlavorRankDto dto) {
        return new FlavorRankResponse(dto.getId(), dto.getContent(), dto.getCount());
    }
}
