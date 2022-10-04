package com.beerair.core.region.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ContinentResponses {

    private final List<ContinentResponse> values;

    public static ContinentResponses from(List<ContinentResponse> continentResponses) {
        return new ContinentResponses(continentResponses);
    }
}
