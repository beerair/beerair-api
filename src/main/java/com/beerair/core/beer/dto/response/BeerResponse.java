package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.beer.dto.query.BeerListItemDto;
import com.beerair.core.region.dto.response.CountryResponse;
import com.beerair.core.review.dto.query.ReviewDto;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.Objects;
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
public class BeerResponse {
	@ApiModelProperty(
		dataType = "Number",
		value = "ID",
		example = "1"
	)
	private String id;

	private CountryResponse country;

	private BeerTypeResponse type;

	private ReviewResponse myReview;

	@ApiModelProperty(
		dataType = "String",
		value = "한글 이름",
		example = "빅슬라이스 IPA"
	)
	private String korName;

	@ApiModelProperty(
		dataType = "String",
		value = "영문 이름",
		example = "Big Slice Ipa"
	)
	private String engName;

	@ApiModelProperty(
		dataType = "String",
		value = "맥주 이미지 URL",
		example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/big_slice_ipa.png"
	)
	private String imageUrl;

	@ApiModelProperty(
		dataType = "String",
		value = "맥주 설명",
		example = "열대 과일향과 몰트의 고소함"
	)
	private String content;

	@ApiModelProperty(
		dataType = "Float",
		value = "도수",
		example = "5.4"
	)
	private Float alcohol;

	@ApiModelProperty(
		dataType = "Number",
		value = "가격",
		example = "3500"
	)
	private Integer price;

	@ApiModelProperty(
		dataType = "Number",
		value = "용량(ml)",
		example = "500"
	)
	private Integer volume;

	@ApiModelProperty(
		dataType = "Boolean",
		value = "사용자 좋아요 여부",
		example = "false"
	)
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
