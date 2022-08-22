package com.beerair.core.auth.domain;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class TokenPurpose {
    public static final String ACCESS = "access";
    public static final String REFRESH = "refresh";
}
