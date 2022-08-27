package com.beerair.core.review.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReviewResponse {
    private String id;
    private String content;
    /* TODO
    private Long departuresCountryId;
    private Long arrivalsCountryId;

     */
    private List<Long> flavorIds;
    private FeelStatus feelStatus;
    private String imageUrl;
    private LocalDateTime createdAt;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .feelStatus(review.getFeelStatus())
                .flavorIds(review.getFlavorIds().getValues())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponse from(BeerDto.ReviewInfo review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .feelStatus(review.getFeelStatus())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponse ofListItem(BeerDto.ReviewInfo review) {
        return ReviewResponse.builder()
                .feelStatus(review.getFeelStatus())
                .build();
    }
}
