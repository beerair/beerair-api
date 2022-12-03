package com.beerair.core.beer.dto.query;

import com.beerair.core.review.domain.vo.FeelStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class BeerListItemDto {
	private final Integer id;
	private final Float alcohol;
	private final String korName;
	private final String imageUrl;
	private final String country;
	private final String type;
	private final FeelStatus myFeelStatus;
	private final Boolean liked;
}
