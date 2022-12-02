package com.beerair.core.common.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EncoderUtil {
    public static String encodeUrl(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}
