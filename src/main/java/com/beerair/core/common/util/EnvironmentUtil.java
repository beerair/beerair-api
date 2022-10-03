package com.beerair.core.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * EnvironmentUtil
 **/
@UtilityClass
public final class EnvironmentUtil {
    public static String STAGING_PROFILE = "staging";
    public static String PROD_PROFILE = "prod";
    public static String LOCAL_PROFILE = "local";
    public static String TEST_PROFILE = "test";

    public static boolean isStaging(Environment env) {
        return Arrays.stream(env.getActiveProfiles())
                .anyMatch(profile -> profile.equals(STAGING_PROFILE));
    }

    public static boolean isProd(Environment env) {
        return Arrays.stream(env.getActiveProfiles())
                .anyMatch(profile -> profile.equals(PROD_PROFILE));
    }

    public static boolean isLocal(Environment env) {
        return Arrays.stream(env.getActiveProfiles())
                .anyMatch(profile -> profile.equals(LOCAL_PROFILE));
    }

    public static boolean isTest(Environment env) {
        return Arrays.stream(env.getActiveProfiles())
                .anyMatch(profile -> profile.equals(TEST_PROFILE));
    }
}
