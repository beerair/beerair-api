package com.beerair.core.auth.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class AuthToken {
    private final String token;
    private final Date expired;

    public AuthToken(String token, Date expired) {
        this.token = token;
        this.expired = expired;
    }
}
