package com.beerair.core.suggest.dto.response;

import com.beerair.core.suggest.domain.Suggest;
import com.beerair.core.suggest.domain.vo.SuggestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SuggestResponse {
    private final Long id;
    private final String beerName;
    private final LocalDateTime completedAt;
    private final String rejectReason;
    private final SuggestStatus status;
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
