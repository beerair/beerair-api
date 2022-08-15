package com.beerair.core.common.util;

import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class IdGenerator {
    public static final int UUID_LENGTH = 50;

    public static String createUUID() {
        return UUID.randomUUID()
                   .toString()
                   .replaceAll("-", "");
    }
}
