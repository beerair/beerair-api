package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.region.domain.Country;
import com.beerair.core.region.domain.vo.rs.CountryResponse;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerResponse {

	private final String id;

	private final CountryResponse country;

	private final BeerTypeResponse type;

	private final ReviewResponse myReview;

	private final String korName;

	private final String engName;

	private final String imageUrl;

	private final String content;

	private final Float alcohol;

	private final Integer price;

	private final Integer volume;

	private final Boolean liked;

	private final LocalDateTime createdAt;

	private final LocalDateTime modifiedAt;

	public static BeerResponse from(BeerDto beerDto) {
		var myReview = Objects.isNull(beerDto.getMyReview()) ?
				null : ReviewResponse.from(beerDto.getMyReview());
		return new BeerResponse(
				beerDto.getBeer().getId(),
				CountryResponse.from(beerDto.getCountry()),
				BeerTypeResponse.from(beerDto.getBeerType()),
				myReview,
				beerDto.getBeer().getKorName(),
				beerDto.getBeer().getEngName(),
				beerDto.getBeer().getImageUrl(),
				beerDto.getBeer().getContent(),
				beerDto.getBeer().getAlcohol(),
				beerDto.getBeer().getPrice(),
				beerDto.getBeer().getVolume(),
				beerDto.getLiked(),
				beerDto.getBeer().getCreatedAt(),
				beerDto.getBeer().getModifiedAt()
		);
	}

	public static BeerResponse of(Beer beer, Country country, BeerType type, Review review, Boolean isLiked) {
		var myReview = Objects.isNull(review) ? null : ReviewResponse.from(review);
		return new BeerResponse(
				beer.getId(),
				CountryResponse.from(country),
				BeerTypeResponse.from(type),
				myReview,
				beer.getKorName(),
				beer.getEngName(),
				beer.getImageUrl(),
				beer.getContent(),
				beer.getAlcohol(),
				beer.getPrice(),
				beer.getVolume(),
				isLiked,
				beer.getCreatedAt(),
				beer.getModifiedAt()
		);
	}

	public static BeerResponse ofListItem(BeerDto beerDto) {
		var myReview = Objects.isNull(beerDto.getMyReview()) ?
				null : ReviewResponse.from(beerDto.getMyReview());
		return BeerResponse.builder()
				.id(beerDto.getBeer().getId())
				.alcohol(beerDto.getBeer().getAlcohol())
				.korName(beerDto.getBeer().getKorName())
				.country(CountryResponse.ofListItem(beerDto.getCountry()))
				.type(BeerTypeResponse.ofListItem(beerDto.getBeerType()))
				.myReview(myReview)
				.imageUrl(beerDto.getBeer().getImageUrl())
				.liked(beerDto.getLiked())
				.build();
	}
}
