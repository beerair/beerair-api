package com.beerair.core.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * TimeUtil
 *
 * @apiNote time util
 **/
public class TimeUtil {

    /**
     * TimeUtil with format
     *
     * @param format 2022-10-12
     * @return String
     **/
    public static String now(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * TimeUtil
     *
     * @return LocalDateTime
     **/
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime from(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
