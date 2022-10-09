package com.beerair.core.review.dto.request;

import com.beerair.core.review.domain.vo.FeelStatus;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    @ApiModelProperty(
        dataType = "String",
        value = "리뷰 내용",
        example = "좋다"
    )
    private String content;

    @ApiModelProperty(
        dataType = "String",
        value = "평점",
        example = "VERY_GOOD"
    )
    private FeelStatus feelStatus;

    @ApiModelProperty(
        dataType = "String",
        value = "이미지 URL (미구현)",
        example = "미구현 상태"
    )
    private String imageUrl;

    @ApiModelProperty(
        dataType = "Boolean",
        value = "다른 사람에게 공개 여부",
        example = "true"
    )
    private Boolean isPublic;

    @ApiModelProperty(
        dataType = "Array[Number]",
        value = "맛 ID 최대 (3개)",
        example = "[1, 2, 3]"
    )
    private List<Long> flavorIds;

    @ApiModelProperty(
        dataType = "String",
        value = "맥주 ID",
        example = "1"
    )
    private String beerId;
}
