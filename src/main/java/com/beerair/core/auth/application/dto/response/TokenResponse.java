package com.beerair.core.auth.application.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private Date expired;
}
