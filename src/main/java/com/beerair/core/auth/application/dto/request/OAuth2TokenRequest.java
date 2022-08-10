package com.beerair.core.auth.application.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
public class OAuth2TokenRequest {
    private String socialType;
    private String token;
    private String status;

    public OAuth2TokenRequest(String socialType, String token, String status) {
        this.socialType = socialType;
        this.token = token;
        this.status = status;
    }
}
