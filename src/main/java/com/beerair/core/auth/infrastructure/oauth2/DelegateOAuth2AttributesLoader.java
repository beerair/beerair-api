package com.beerair.core.auth.infrastructure.oauth2;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public abstract class DelegateOAuth2AttributesLoader extends OAuth2AttributesLoader {
    private final DefaultOAuth2UserService delegate;

    public DelegateOAuth2AttributesLoader() {
        this.delegate = new DefaultOAuth2UserService();
    }

    public final OAuth2Attributes load(OAuth2UserRequest request) {
        if (!isLoadable(request)) {
            return next(request);
        }
        var defaultOAuth2User = (DefaultOAuth2User) delegate.loadUser(request);
        return convert(
                defaultOAuth2User,
                getAttributesKey(request)
        );
    }

    protected abstract boolean isLoadable(OAuth2UserRequest request);

    private String getAttributesKey(OAuth2UserRequest request) {
        return request
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
    }

    protected abstract OAuth2Attributes convert(DefaultOAuth2User user, String attributesKey);
}
