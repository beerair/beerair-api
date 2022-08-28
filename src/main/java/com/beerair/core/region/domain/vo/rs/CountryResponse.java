package com.beerair.core.region.domain.vo.rs;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.region.domain.Country;
import com.beerair.core.review.dto.query.ReviewDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
public class CountryResponse {

	private Long id;

	private String korName;

	private String engName;

	private String backgroundImageUrl;

	private String imageUrl;

	public static CountryResponse from(Country country) {
		return new CountryResponse(
				country.getId(),
				country.getKorName(),
				country.getEngName(),
				country.getBackgroundImageUrl(),
				country.getImageUrl());
	}

	public static CountryResponse from(BeerDto.CountryInfo countryInfo) {
		return new CountryResponse(
				countryInfo.getId(),
				countryInfo.getKorName(),
				countryInfo.getEngName(),
				countryInfo.getBackgroundImageUrl(),
				countryInfo.getImageUrl());
	}

	public static CountryResponse from(ReviewDto.CountryInfo countryInfo) {
		return CountryResponse.builder()
				.korName(countryInfo.getKorName())
				.engName(countryInfo.getEngName())
				.build();
	}
}
