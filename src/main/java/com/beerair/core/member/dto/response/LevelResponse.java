package com.beerair.core.member.dto.response;

import com.beerair.core.member.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LevelResponse {
    private Integer id;
    private String imageUrl;
    private Integer exp;
    private Integer tier;

    public static LevelResponse from(Level level) {
        return LevelResponse.builder()
                .id(level.getId())
                .imageUrl(level.getImageUrl())
                .exp(level.getExp())
                .tier(level.getTier())
                .build();
    }
}
