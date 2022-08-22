package com.beerair.core.review.dto.request;

import com.beerair.core.review.domain.FlavorIds;
import com.beerair.core.review.domain.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReviewRequest {

    @JsonProperty
    private final String content;

    @JsonProperty
    private final Integer feel;

    @JsonProperty
    private final String imageUrl;

    @JsonProperty
    private final Boolean isPublic;

    @JsonProperty
    private final List<Long> flavorIds;

    @JsonProperty
    private final Long beerId;

    public Review entity(Long departuresCountryId, Long arrivalsCountryId, String memberId) {
        return Review.of(content, departuresCountryId, arrivalsCountryId, feel,
                imageUrl, isPublic, FlavorIds.from(flavorIds) ,beerId, memberId);
    }
}
