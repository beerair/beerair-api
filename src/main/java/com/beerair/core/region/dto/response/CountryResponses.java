package com.beerair.core.region.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CountryResponses {

    private final List<CountryResponse> values;

    public static CountryResponses from(List<CountryResponse> countryResponses) {
        return new CountryResponses(countryResponses);
    }
}
