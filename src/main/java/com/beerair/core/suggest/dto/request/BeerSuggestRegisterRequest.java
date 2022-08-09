package com.beerair.core.suggest.dto.request;

import lombok.Data;

@Data
public class BeerSuggestRegisterRequest {
    private final String name;
    private final BeerSuggestImageRegisterRequest images;

    @Data
    public static class BeerSuggestImageRegisterRequest {
        private final String beerImage1;
        private final String beerImage2;
    }
}
