package com.beerair.core.beer.dto.query;

import com.beerair.core.review.domain.vo.FeelStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BeerListItemDto {
	private String id;
	private Float alcohol;
	private String korName;
	private String imageUrl;
	private String country;
	private String type;
	private FeelStatus myFeelStatus;
	private Boolean liked;

	public BeerListItemDto(String id, Float alcohol, String korName, String imageUrl, String country, String type) {
		this.id = id;
		this.alcohol = alcohol;
		this.korName = korName;
		this.imageUrl = imageUrl;
		this.country = country;
		this.type = type;
		this.myFeelStatus = null;
		this.liked = false;
	}
}
