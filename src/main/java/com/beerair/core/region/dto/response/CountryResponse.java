package com.beerair.core.region.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.region.domain.Country;
import com.beerair.core.review.dto.query.ReviewDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(
		dataType = "Number",
		value = "ID",
		example = "1"
	)
	private Long id;

	@ApiModelProperty(
		dataType = "String",
		value = "한글 명",
		example = "대한민국"
	)
	private String korName;

	@ApiModelProperty(
		dataType = "String",
		value = "영문 명",
		example = "korea"
	)
	private String engName;

	@ApiModelProperty(
		dataType = "String",
		value = "백그라운드 이미지",
		example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/background/korea.png"
	)
	private String backgroundImageUrl;

	@ApiModelProperty(
		dataType = "String",
		value = "이미지",
		example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/korea.png"
	)
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
				.id(countryInfo.getId())
				.korName(countryInfo.getKorName())
				.engName(countryInfo.getEngName())
				.build();
	}
}
