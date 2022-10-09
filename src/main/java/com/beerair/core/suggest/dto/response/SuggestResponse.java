package com.beerair.core.suggest.dto.response;

import com.beerair.core.suggest.domain.Suggest;
import com.beerair.core.suggest.domain.vo.SuggestStatus;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SuggestResponse {
    @ApiModelProperty(
        dataType = "Number",
        value = "ID",
        example = "1"
    )
    private final Long id;

    @ApiModelProperty(
        dataType = "String",
        value = "맥주 이름",
        example = "참이슬"
    )
    private final String beerName;

    @ApiModelProperty(
        dataType = "Date",
        value = "제안 일자",
        example = "2022-09-17TZ12:44:56.000"
    )
    private final LocalDateTime completedAt;

    @ApiModelProperty(
        dataType = "String",
        value = "거부 사유",
        example = "소주임"
    )
    private final String rejectReason;

    @ApiModelProperty(
        dataType = "String",
        value = "상태",
        example = "REJECT"
    )
    private final SuggestStatus status;

    @ApiModelProperty(
        dataType = "String",
        value = "상태 설명",
        example = "거부"
    )
    private final String statusDescription;

    public SuggestResponse(Suggest suggest) {
        this.id = suggest.getId();
        this.beerName = suggest.getBeerName();
        this.completedAt = suggest.getCompletedAt();
        this.rejectReason = suggest.getRejectReason();
        this.status = suggest.getStatus();
        this.statusDescription = suggest.getStatus().getDescription();
    }
}
