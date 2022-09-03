package com.beerair.core.review.dto.response;

import com.beerair.core.review.dto.query.FlavorRankDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FlavorRankResponse {
    private Long id;
    private String content;
    private int count;

    public static FlavorRankResponse from(FlavorRankDto dto) {
        return new FlavorRankResponse(dto.getId(), dto.getContent(), dto.getCount());
    }
}
