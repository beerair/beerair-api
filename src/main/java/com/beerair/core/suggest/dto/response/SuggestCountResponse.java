package com.beerair.core.suggest.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SuggestCountResponse {
    @ApiModelProperty(
        dataType = "String",
        value = "사용자 ID",
        example = "9a7cc50525eb4df286e0096b96874c38"
    )
    private final String memberId;

    @ApiModelProperty(
        dataType = "Number",
        value = "갯수",
        example = "1"
    )
    private final Long count;
}
