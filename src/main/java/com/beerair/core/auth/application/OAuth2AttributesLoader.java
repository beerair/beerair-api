package com.beerair.core.auth.application;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

public interface OAuth2AttributesLoader {
    boolean isLoadable(OAuth2UserRequest request);

    OAuth2Attributes load(OAuth2UserRequest request);
}
