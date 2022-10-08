package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.dto.query.BeerDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerTypeResponse {
    @ApiModelProperty(
        dataType = "Number",
        value = "ID",
        example = "1"
    )
    private Long id;

    @ApiModelProperty(
        dataType = "String",
        value = "한글 이름",
        example = "에일"
    )
    private String korName;

    @ApiModelProperty(
        dataType = "String",
        value = "영문 이름",
        example = "ALE"
    )
    private String engName;

    @ApiModelProperty(
        dataType = "String",
        value = "설명",
        example = "쓴 맛이 적고 단 맛이 나며, 풀 바디감이 느껴지는 목 넘김이 깔끔한 맥주"
    )
    private String content;

    @ApiModelProperty(
        dataType = "String",
        value = "이미지 URL",
        example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/ale.png"
    )
    private String imageUrl;

    public static BeerTypeResponse from(BeerType beerType) {
        return new BeerTypeResponse(beerType.getId(), beerType.getKorName(), beerType.getEngName(), beerType.getContent(), beerType.getImageUrl());
    }

    public static BeerTypeResponse from(BeerDto.BeerTypeInfo beerTypeInfo) {
        return new BeerTypeResponse(beerTypeInfo.getId(), beerTypeInfo.getKorName(), beerTypeInfo.getEngName(),
                                    beerTypeInfo.getContent(),
                                    beerTypeInfo.getImageUrl());
    }
}
