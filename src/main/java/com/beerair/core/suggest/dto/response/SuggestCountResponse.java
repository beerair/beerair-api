package com.beerair.core.suggest.dto.response;

import lombok.Data;

@Data
public class SuggestCountResponse {
    private final String memberId;
    private final Long count;
}
