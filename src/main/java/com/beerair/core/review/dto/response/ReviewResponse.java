package com.beerair.core.review.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.member.dto.response.MemberResponse;
import com.beerair.core.region.dto.response.CountryResponse;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.dto.query.ReviewDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReviewResponse {
    @ApiModelProperty(
        dataType = "Number",
        value = "리뷰 ID",
        example = "1"
    )
    private Integer id;

    @ApiModelProperty(
        dataType = "String",
        value = "리뷰 내용",
        example = "맛있다"
    )
    private String content;

    @ApiModelProperty(
        dataType = "Number",
        value = "평점",
        example = "5"
    )
    private Integer feelStatus;

    @ApiModelProperty(
        dataType = "String",
        value = "업로드한 이미지",
        example = "미구현"
    )
    private String imageUrl;

    @ApiModelProperty(
        dataType = "String",
        value = "평점",
        example = "2022-09-17TZ12:44:56.000"
    )
    private LocalDateTime createdAt;

    private MemberResponse member;
    private BeerResponse beer;
    private CountryResponse departuresCountry;
    private CountryResponse arrivalCountry;
    private List<FlavorResponse> flavors;

    public static ReviewResponse from(ReviewDto dto) {
        return ReviewResponse.builder()
                .id(dto.getReview().getId())

                .beer(BeerResponse.from(dto.getBeer()))
                .departuresCountry(CountryResponse.from(dto.getDeparturesCountry()))
                .arrivalCountry(CountryResponse.from(dto.getArrivalCountry()))
                .flavors(flavors(dto))

                .content(dto.getReview().getContent())
                .imageUrl(dto.getReview().getImageUrl())
                .feelStatus(dto.getReview().getFeelStatus().getScore())
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

    public static ReviewResponse ofListItemAtMe(ReviewDto dto) {
        BeerResponse beer = BeerResponse.builder()
                .id(dto.getBeer().getId())
                .korName(dto.getBeer().getKorName())
                .engName(dto.getBeer().getEngName())
                .build();
        return ReviewResponse.builder()
                .id(dto.getReview().getId())
                .beer(beer)
                .departuresCountry(CountryResponse.from(dto.getDeparturesCountry()))
                .arrivalCountry(CountryResponse.from(dto.getArrivalCountry()))
                .feelStatus(dto.getReview().getFeelStatus().getScore())
                .createdAt(dto.getReview().getCreatedAt())
                .build();
    }

    public static ReviewResponse ofListItemAtBeer(ReviewDto dto) {
        MemberResponse member = MemberResponse.builder()
                .id(dto.getMember().getId())
                .nickname(dto.getMember().getNickname())
                .build();
        return ReviewResponse.builder()
                .id(dto.getReview().getId())
                .member(member)
                .flavors(flavors(dto))
                .feelStatus(dto.getReview().getFeelStatus().getScore())
                .content(dto.getReview().getContent())
                .createdAt(dto.getReview().getCreatedAt())
                .build();
    }

    public static ReviewResponse from(BeerDto.ReviewInfo review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .feelStatus(review.getFeelStatus().getScore())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
