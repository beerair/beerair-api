package com.beerair.core.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
}
