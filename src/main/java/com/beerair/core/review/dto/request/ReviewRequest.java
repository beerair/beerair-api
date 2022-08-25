package com.beerair.core.review.dto.request;

import com.beerair.core.review.domain.FlavorIds;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReviewRequest {

    @JsonProperty
    private final String content;

    @JsonProperty
    private final FeelStatus feelStatus;

    @JsonProperty
    private final String imageUrl;

    @JsonProperty
    private final Boolean isPublic;

    @JsonProperty
    private final List<Long> flavorIds;

    @JsonProperty
    private final String beerId;

    public Review entity(Long departuresCountryId, Long arrivalsCountryId, String memberId) {
        return Review.builder()
                .content(content)
                .departuresCountryId(departuresCountryId)
                .arrivalsCountryId(arrivalsCountryId)
                .feelStatus(feelStatus)
                .imageUrl(imageUrl)
                .isPublic(isPublic)
                .flavorIds(FlavorIds.from(flavorIds))
                .beerId(beerId)
                .memberId(memberId)
                .build();
    }
}
