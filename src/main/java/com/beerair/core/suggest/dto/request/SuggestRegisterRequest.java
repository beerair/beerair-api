package com.beerair.core.suggest.dto.request;

import lombok.Data;

@Data
public class SuggestRegisterRequest {
    private final String name;
    private final SuggestImageRegisterRequest images;

    @Data
    public static class SuggestImageRegisterRequest {
        private final String beerImage1;
        private final String beerImage2;
    }
}
