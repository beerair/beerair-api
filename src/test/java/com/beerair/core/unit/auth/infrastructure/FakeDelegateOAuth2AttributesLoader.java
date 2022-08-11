package com.beerair.core.unit.auth.infrastructure;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.beerair.core.auth.domain.OAuth2Attributes;
import com.beerair.core.auth.infrastructure.DelegateOAuth2AttributesLoader;
import com.beerair.core.fixture.Fixture;

import lombok.Getter;
import lombok.Setter;

@Getter
public class FakeDelegateOAuth2AttributesLoader extends DelegateOAuth2AttributesLoader {
    @Setter
    private boolean loadable;
    private boolean converted = false;

    public FakeDelegateOAuth2AttributesLoader(DefaultOAuth2UserService delegate) {
        new Fixture<>(this).set("delegate", delegate);
    }

    @Override
    protected boolean isLoadable(OAuth2UserRequest request) {
        return loadable;
    }

    @Override
    protected OAuth2Attributes convert(DefaultOAuth2User user) {
        converted = true;
        return null;
    }
}
