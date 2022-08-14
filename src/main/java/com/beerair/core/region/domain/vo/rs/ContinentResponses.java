package com.beerair.core.region.domain.vo.rs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ContinentResponses {

    private final List<ContinentResponse> values;

    public static ContinentResponses from(List<ContinentResponse> continentResponses) {
        return new ContinentResponses(continentResponses);
    }
}
