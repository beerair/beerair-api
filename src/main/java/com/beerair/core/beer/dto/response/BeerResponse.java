package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.beer.dto.query.BeerListItemDto;
import com.beerair.core.region.domain.vo.rs.CountryResponse;
import com.beerair.core.review.dto.query.ReviewDto;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerResponse {

	private String id;

	private CountryResponse country;

	private BeerTypeResponse type;

	private ReviewResponse myReview;

	private String korName;

	private String engName;

	private String imageUrl;

	private String content;

	private Float alcohol;

	private Integer price;

	private Integer volume;

	private Boolean liked;


	public static BeerResponse from(BeerDto beerDto) {
		var myReview = Objects.isNull(beerDto.getMyReview()) ?
				null : ReviewResponse.from(beerDto.getMyReview());
		var beerInfo = beerDto.getBeer();
		return BeerResponse.builder()
				.id(beerInfo.getId())
				.country(CountryResponse.from(beerDto.getCountry()))
				.type(BeerTypeResponse.from(beerDto.getBeerType()))
				.myReview(myReview)
				.korName(beerInfo.getKorName())
				.engName(beerInfo.getEngName())
				.imageUrl(beerInfo.getImageUrl())
				.content(beerInfo.getContent())
				.alcohol(beerInfo.getAlcohol())
				.price(beerInfo.getPrice())
				.volume(beerInfo.getVolume())
				.liked(beerDto.getLiked())
				.build();
	}

	public static BeerResponse from(ReviewDto.BeerInfo beerInfo) {
		return BeerResponse.builder()
				.korName(beerInfo.getKorName())
				.engName(beerInfo.getEngName())
				.alcohol(beerInfo.getAlcohol())
				.build();
	}

	public static BeerResponse ofListItem(BeerListItemDto beerDto) {
		var country = CountryResponse.builder()
				.korName(beerDto.getCountry())
				.build();
		var type = BeerTypeResponse.builder()
				.korName(beerDto.getKorName())
				.build();
		ReviewResponse myReview = null;
		if (Objects.nonNull(beerDto.getMyFeelStatus())) {
			myReview = ReviewResponse.builder()
					.feelScore(beerDto.getMyFeelStatus().getScore())
					.feelDescription(beerDto.getMyFeelStatus().getDescription())
					.build();
		}
		return BeerResponse.builder()
				.id(beerDto.getId())
				.alcohol(beerDto.getAlcohol())
				.korName(beerDto.getKorName())
				.imageUrl(beerDto.getImageUrl())
				.country(country)
				.type(type)
				.myReview(myReview)
				.liked(beerDto.getLiked())
				.build();
	}
}
