package com.beerair.core.auth.dto.response;

import com.beerair.core.auth.domain.AuthToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "토큰 발급 DTO (Response에 사용 되지 않음)")
@Builder
@AllArgsConstructor
@Getter
public class TokenRefreshResponse {
    @Schema(defaultValue = "access token (만료 시간 : 1시간)")
    private AuthToken accessToken;

    @Schema(defaultValue = "refresh token (만료 시간 : 48시간)")
    private AuthToken refreshToken;
}
