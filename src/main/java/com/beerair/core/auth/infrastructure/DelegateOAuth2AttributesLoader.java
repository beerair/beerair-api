package com.beerair.core.auth.infrastructure;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.beerair.core.auth.application.OAuth2AttributesLoader;
import com.beerair.core.auth.domain.OAuth2Attributes;

public abstract class DelegateOAuth2AttributesLoader extends OAuth2AttributesLoader {
    private final DefaultOAuth2UserService delegate;

    public DelegateOAuth2AttributesLoader() {
        this.delegate = new DefaultOAuth2UserService();
    }

    public OAuth2Attributes load(OAuth2UserRequest request) {
        if (!isLoadable(request)) {
            return next(request);
        }
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) delegate.loadUser(request);
        return convert(defaultOAuth2User);
    }

    protected abstract boolean isLoadable(OAuth2UserRequest request);

    protected abstract OAuth2Attributes convert(DefaultOAuth2User user);
}
