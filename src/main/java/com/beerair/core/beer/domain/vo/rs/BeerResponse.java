package com.beerair.core.beer.domain.vo.rs;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.domain.vo.BeerDto;
import com.beerair.core.region.domain.Country;
import com.beerair.core.region.domain.vo.rs.CountryResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerResponse {

	private final Long id;

	private final CountryResponse country;

	private final BeerTypeResponse type;

	private final String korName;

	private final String engName;

	private final String imageUrl;

	private final String content;

	private final Float alcohol;

	private final Integer price;

	private final Integer volume;

	private final Boolean isLiked;

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
				beerDto.getIsLiked(),
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
}
