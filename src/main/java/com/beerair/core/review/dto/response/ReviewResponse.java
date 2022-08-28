package com.beerair.core.review.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.region.domain.vo.rs.CountryResponse;
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
    private BeerResponse beer;
    private CountryResponse departuresCountry;
    private CountryResponse arrivalCountry;
    private List<Long> flavorIds;
    private int feelScore;
    private String feelDescription;
    private String imageUrl;
    private LocalDateTime createdAt;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .feelScore(review.getFeelStatus().getScore())
                .feelDescription(review.getFeelStatus().getDescription())
                .flavorIds(review.getFlavorIds().getValues())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponse from(BeerDto.ReviewInfo review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .feelScore(review.getFeelStatus().getScore())
                .feelDescription(review.getFeelStatus().getDescription())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
