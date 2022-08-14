package com.beerair.core.region.domain.vo.rs;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.region.domain.Country;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CountryResponse {

	private final Long id;

	private final String korName;

	private final String engName;

	private final String backgroundImageUrl;

	private final String imageUrl;

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
}
