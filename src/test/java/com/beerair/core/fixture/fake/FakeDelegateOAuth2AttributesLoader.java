package com.beerair.core.fixture.fake;

import com.beerair.core.auth.infrastructure.oauth2.DelegateOAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import com.beerair.core.fixture.Fixture;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.mock;

@Getter
public class FakeDelegateOAuth2AttributesLoader extends DelegateOAuth2AttributesLoader {
    @Setter
    private boolean loadable;
    private boolean converted = false;

    public FakeDelegateOAuth2AttributesLoader() {
        new Fixture<>(this).set("delegate", mock(DefaultOAuth2UserService.class));
    }

    public DefaultOAuth2UserService getDelegate() {
        return new Fixture<>(this).getField("delegate");
    }

    @Override
    protected boolean isLoadable(OAuth2UserRequest request) {
        return loadable;
    }

    @Override
    protected OAuth2Attributes convert(DefaultOAuth2User user, String attributeKey) {
        converted = true;
        return null;
    }
}
