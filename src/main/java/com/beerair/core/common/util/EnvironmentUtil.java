package com.beerair.core.common.util;

import java.util.Arrays;
import org.springframework.core.env.Environment;

/**
 * EnvironmentUtil
 **/
public class EnvironmentUtil {
    public static String STAGING_PROFILE = "staging";
    public static String PROD_PROFILE = "prod";

    public static boolean isStaging(Environment environment) {
        return Arrays.stream(environment.getActiveProfiles())
                .anyMatch(profile -> profile.equals(STAGING_PROFILE));
    }

    public static boolean isProd(Environment environment) {
        return Arrays.stream(environment.getActiveProfiles())
                .anyMatch(profile -> profile.equals(PROD_PROFILE));
    }
}
