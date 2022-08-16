package com.beerair.core.suggest.dto.response;

import lombok.Data;

@Data
public class BeerSuggestCountResponse {
    private final String memberId;
    private final Long count;
}
