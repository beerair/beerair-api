package com.beerair.core.region.dto.response;

import com.beerair.core.region.domain.Continent;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ContinentResponse {

    private final Long id;
    private final String korName;
    private final String engName;

    public static ContinentResponse of(Long id, String korName, String engName) {
        return new ContinentResponse(id, korName, engName);
    }

    public static ContinentResponse from(Continent continent) {
        return new ContinentResponse(continent.getId(), continent.getKorName(), continent.getEngName());
    }
}
