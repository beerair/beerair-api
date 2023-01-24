package com.beerair.core.suggest.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SuggestRegisterRequest {
    @ApiModelProperty(
        dataType = "String",
        value = "맥주 이름",
        example = "참이슬"
    )
    private final String name;
    private final List<String> images;

    @Data
    public static class SuggestImageModel {
        @ApiModelProperty(
            dataType = "String",
            value = "맥주 이미지1",
            example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/big_slice_ipa.png"
        )
        private final String beerImage1;

        @ApiModelProperty(
            dataType = "String",
            value = "맥주 이미지2",
            example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/super_swing_lager.png"
        )
        private final String beerImage2;
    }
}
