package com.beerair.core.review.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.region.domain.vo.rs.CountryResponse;
import com.beerair.core.review.dto.query.ReviewDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReviewResponse {
    private String id;
    private BeerResponse beer;
    private CountryResponse departuresCountry;
    private CountryResponse arrivalCountry;
    private List<FlavorResponse> flavors;
    private String content;
    private int feelScore;
    private String feelDescription;
    private String imageUrl;
    private LocalDateTime createdAt;

    public static ReviewResponse from(ReviewDto dto) {
        return ReviewResponse.builder()
                .id(dto.getReview().getId())

                .beer(BeerResponse.from(dto.getBeer()))
                .departuresCountry(CountryResponse.from(dto.getDeparturesCountry()))
                .arrivalCountry(CountryResponse.from(dto.getArrivalCountry()))
                .flavors(flavors(dto))

                .content(dto.getReview().getContent())
                .imageUrl(dto.getReview().getImageUrl())
                .feelScore(dto.getReview().getFeelStatus().getScore())
                .feelDescription(dto.getReview().getFeelStatus().getDescription())
                .createdAt(dto.getReview().getCreatedAt())


                .build();
    }

    private static List<FlavorResponse> flavors(ReviewDto dto) {
        return Stream.of(
                        dto.getFlavor1(),
                        dto.getFlavor2(),
                        dto.getFlavor3()
                )
                .filter(Objects::nonNull)
                .map(each -> new FlavorResponse(null, each.getContent()))
                .collect(Collectors.toList());
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
