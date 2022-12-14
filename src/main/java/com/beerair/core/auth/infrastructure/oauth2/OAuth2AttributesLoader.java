package com.beerair.core.auth.infrastructure.oauth2;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import lombok.Setter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

public abstract class OAuth2AttributesLoader {
    @Setter
    private OAuth2AttributesLoader next;

    public abstract OAuth2Attributes load(OAuth2UserRequest request);

    protected final OAuth2Attributes next(OAuth2UserRequest request) {
        return next.load(request);
    }
}
