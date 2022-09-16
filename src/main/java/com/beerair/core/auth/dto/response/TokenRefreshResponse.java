package com.beerair.core.auth.dto.response;

import com.beerair.core.auth.domain.AuthToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TokenRefreshResponse {
    private AuthToken accessToken;
    private AuthToken refreshToken;
}
