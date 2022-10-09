package com.beerair.core.region.dto.response;

import com.beerair.core.region.domain.Continent;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ContinentResponse {
    @ApiModelProperty(
        dataType = "Number",
        value = "ID",
        example = "1"
    )
    private final Long id;

    @ApiModelProperty(
        dataType = "String",
        value = "한글 명",
        example = "아시아"
    )
    private final String korName;

    @ApiModelProperty(
        dataType = "String",
        value = "영문 명",
        example = "Asia"
    )
    private final String engName;

    public static ContinentResponse of(Long id, String korName, String engName) {
        return new ContinentResponse(id, korName, engName);
    }

    public static ContinentResponse from(Continent continent) {
        return new ContinentResponse(continent.getId(), continent.getKorName(), continent.getEngName());
    }
}
