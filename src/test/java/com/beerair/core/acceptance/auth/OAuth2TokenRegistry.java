package com.beerair.core.acceptance.auth;

import java.util.HashMap;
import java.util.Map;

import com.beerair.core.auth.application.dto.request.OAuth2TokenRequest;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OAuth2TokenRegistry {
    private static final Map<OAuth2TokenRequest, String> tokenEachEmail = new HashMap<>();

    public static void register(OAuth2TokenRequest request, String email) {
        tokenEachEmail.put(request, email);
    }

    public static String getEmail(OAuth2TokenRequest request) {
        return tokenEachEmail.get(request);
    }

    public static void clear() {
        tokenEachEmail.clear();
    }
}
