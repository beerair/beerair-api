package com.beerair.core.common.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public final class KeyGenerator {
    public static final int UUID_LENGTH = 50;

    public static String createKeyByUUID() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-", "");
    }
}
