package com.beerair.core.auth.infrastructure;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.beerair.core.auth.application.OAuth2AttributesLoader;
import com.beerair.core.auth.application.OAuth2Attributes;

public abstract class DefaultDelegateOAuth2AttributesLoader implements OAuth2AttributesLoader {
    private final DefaultOAuth2UserService delegate;

    public DefaultDelegateOAuth2AttributesLoader() {
        this.delegate = new DefaultOAuth2UserService();
    }

    public abstract boolean isLoadable(OAuth2UserRequest request);

    public OAuth2Attributes load(OAuth2UserRequest request) {
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) delegate.loadUser(request);
        return convert(defaultOAuth2User);
    }

    protected abstract OAuth2Attributes convert(DefaultOAuth2User user);
}
