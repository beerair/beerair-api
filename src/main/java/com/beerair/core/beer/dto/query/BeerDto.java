package com.beerair.core.beer.dto.query;

import com.beerair.core.review.domain.vo.ReviewFlavors;
import com.beerair.core.review.domain.vo.FeelStatus;

import java.time.LocalDateTime;

public interface BeerDto {

	BeerInfo getBeer();

	CountryInfo getCountry();

	BeerTypeInfo getBeerType();

	ReviewInfo getMyReview();

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

	interface ReviewInfo {
		String getId();
		FeelStatus getFeelStatus();
		String getContent();
		String getImageUrl();
		ReviewFlavors getFlavorIds();
		LocalDateTime getCreatedAt();
	}
}
