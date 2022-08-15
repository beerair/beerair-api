package com.beerair.core.unit.auth.infrastructure.oauth2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.beerair.core.fixture.fake.FakeDelegateOAuth2AttributesLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;

@ExtendWith(MockitoExtension.class)
public class DelegateOAuth2AttributesLoaderTest {
    @DisplayName("delegate 에게 OAuth2를 요청한 후 자식 객체에게 OAuth2Attributes 변환을 위윔한다.")
    @Test
    void load() {
        FakeDelegateOAuth2AttributesLoader loader = new FakeDelegateOAuth2AttributesLoader();

        loader.setLoadable(true);
        loader.load(createRequest());

        assertThat(loader.isConverted()).isTrue();
    }

    private OAuth2UserRequest createRequest() {
        ClientRegistration registration = ClientRegistration
            .withRegistrationId("beerair")
            .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
            .userNameAttributeName("aaa")
            .build();

        var request = mock(OAuth2UserRequest.class);
        when(request.getClientRegistration())
            .thenReturn(registration);
        return request;
    }

    @DisplayName("isLoadable 의 반환이 false라면 다음 체인으로 넘긴다.")
    @Test
    void isLoadableFalse() {
        OAuth2AttributesLoader nextChain = mock(OAuth2AttributesLoader.class);
        FakeDelegateOAuth2AttributesLoader loader = new FakeDelegateOAuth2AttributesLoader();
        loader.setNext(nextChain);

        loader.setLoadable(false);
        loader.load(any());

        verify(nextChain, times(1)).load(any());
    }
}
