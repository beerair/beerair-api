package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.region.domain.Country;
import com.beerair.core.region.domain.vo.rs.CountryResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerResponse {

	private final String id;

	private final CountryResponse country;

	private final BeerTypeResponse type;

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
		return new BeerResponse(
				beerDto.getBeer().getId(),
				CountryResponse.from(beerDto.getCountry()),
				BeerTypeResponse.from(beerDto.getBeerType()),
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

	public static BeerResponse of(Beer beer, Country country, BeerType type, Boolean isLiked) {
		return new BeerResponse(
				beer.getId(),
				CountryResponse.from(country),
				BeerTypeResponse.from(type),
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
		return BeerResponse.builder()
				.id(beerDto.getBeer().getId())
				.alcohol(beerDto.getBeer().getAlcohol())
				.korName(beerDto.getBeer().getKorName())
				.country(CountryResponse.ofListItem(beerDto.getCountry()))
				.type(BeerTypeResponse.ofListItem(beerDto.getBeerType()))
				.imageUrl(beerDto.getBeer().getImageUrl())
				.liked(beerDto.getLiked())
				.build();
	}
}
