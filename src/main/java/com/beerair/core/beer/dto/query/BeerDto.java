package com.beerair.core.beer.dto.query;

import com.beerair.core.review.domain.vo.FeelStatus;

import java.time.LocalDateTime;

public interface BeerDto {

	BeerInfo getBeer();

	CountryInfo getCountry();

	BeerTypeInfo getBeerType();

	Boolean getLiked();

	interface BeerInfo {

		String getId();

		String getKorName();

		String getEngName();

		String getImageUrl();

		String getContent();

		Float getAlcohol();

		Integer getPrice();

		Integer getVolume();

		LocalDateTime getCreatedAt();

		LocalDateTime getModifiedAt();
	}
	interface CountryInfo {

		Long getId();

		String getKorName();

		String getEngName();

		String getBackgroundImageUrl();

		String getImageUrl();
	}

	interface BeerTypeInfo {

		Long getId();

		String getKorName();

		String getEngName();

		String getContent();

		String getImageUrl();
	}

	interface MyReviewInfo {
		FeelStatus getFeelStatus();
		String getContent();
		String getImage();
	}
}
