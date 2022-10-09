package com.beerair.core.auth.domain;

import java.util.Date;
import lombok.Getter;

@Getter
public class AuthToken {
    private final String token;
    private final Date expired;

    public AuthToken(String token, Date expired) {
        this.token = token;
        this.expired = expired;
    }
}
