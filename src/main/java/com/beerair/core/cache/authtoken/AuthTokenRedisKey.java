package com.beerair.core.cache.authtoken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthTokenRedisKey {
    AUTH_REFRESH_TOKEN("auth:token:refresh-token"),
    ;

    private final String prefix;

    public String cacheKey(String key) {
        return this.prefix + ":" + key;
    }
}
