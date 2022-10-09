package com.beerair.core.suggest.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SuggestRegisterResponse {
    @ApiModelProperty(
        dataType = "Number",
        value = "제안 ID",
        example = "1"
    )
    private final Long id;

    @ApiModelProperty(
        dataType = "String",
        value = "맥주 이름",
        example = "참이슬"
    )
    private final String name;
}
