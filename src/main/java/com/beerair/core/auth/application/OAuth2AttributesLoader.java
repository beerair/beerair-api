package com.beerair.core.auth.application;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import com.beerair.core.auth.domain.OAuth2Attributes;

public abstract class OAuth2AttributesLoader {
    private OAuth2AttributesLoader next;

    public abstract OAuth2Attributes load(OAuth2UserRequest request);

    public final void setNextChain(OAuth2AttributesLoader loader) {
        this.next = loader;
    }

    protected final OAuth2Attributes next(OAuth2UserRequest request) {
        return next.load(request);
    }
}
