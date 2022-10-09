package com.beerair.core.member.dto.response;

import com.beerair.core.member.domain.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LevelResponse {
    @ApiModelProperty(
        dataType = "Number",
        value = "ID",
        example = "5"
    )
    private Integer id;

    @ApiModelProperty(
        dataType = "Number",
        value = "이미지 URL",
        example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/MEMBER/LEVEL/5.png"
    )
    private String imageUrl;

    @ApiModelProperty(
        dataType = "Number",
        value = "요구 경험치",
        example = "20",
        required = true
    )
    private Integer exp;

    @ApiModelProperty(
        dataType = "Number",
        value = "레벨",
        example = "5"
    )
    private Integer tier;

    public static LevelResponse from(Level level) {
        return LevelResponse.builder()
            .id(level.getId())
            .imageUrl(level.getImageUrl())
            .exp(level.getExp())
            .tier(level.getTier())
            .build();
    }
}
