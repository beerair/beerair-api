package com.beerair.core.unit.auth.infrastructure;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.beerair.core.auth.infrastructure.NaverOAuth2AttributesLoader;
import com.beerair.core.fixture.Fixture;

@Tag("Unit")
@ExtendWith(MockitoExtension.class)
class NaverOAuth2AttributesLoaderTest {
    private NaverOAuth2AttributesLoader loader;
    @Mock
    private OAuth2UserRequest request;

    @BeforeEach
    void setUp() {
        loader = new NaverOAuth2AttributesLoader();
    }

    @DisplayName("ClientRegistration.RegistrationId가 'naver' 라면 Attributes를 변환할 수 있다.")
    @Test
    void isLoadable() {
        stubbingGetClientRegistration();

        assertThat(loader.isLoadable(request))
            .isTrue();
    }

    private void stubbingGetClientRegistration() {
        ClientRegistration registration = ClientRegistration
            .withRegistrationId("naver")
            .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
            .build();

        when(request.getClientRegistration())
            .thenReturn(registration);
    }

    @DisplayName("DefaultOAuth2User attributes 를 OAuth2Attributes spec 으로 변환한다.")
    @Test
    void convert() {
        DefaultOAuth2UserService stub = mockingDefaultOAuth2UserService();
        var user = createUser("response", "00", "message");
        stubbingLoadUser(stub, user);

        var expert = loader.load(request);
        assertThat(expert.getName())
            .isEqualTo("김재원");
    }

    @DisplayName("result code가 '00'이 아니면 BadCredentialsException 오류를 던진다.")
    @Test
    void convertFail1() {
        DefaultOAuth2UserService stub = mockingDefaultOAuth2UserService();
        var user = createUser("response", "01", "잘못 되었다.");
        stubbingLoadUser(stub, user);

        assertThatThrownBy(() -> loader.load(request))
            .isInstanceOf(BadCredentialsException.class);
    }

    private DefaultOAuth2UserService mockingDefaultOAuth2UserService() {
        DefaultOAuth2UserService stub = mock(DefaultOAuth2UserService.class);
        new Fixture<>(loader).set("delegate", stub);
        return stub;
    }

    private void stubbingLoadUser(DefaultOAuth2UserService service, OAuth2User user) {
        when(service.loadUser(request))
            .thenReturn(user);
    }

    @SuppressWarnings({"SameParameterValue", "rawtypes", "unchecked"})
    private DefaultOAuth2User createUser(String nameAttributeKey, String resultCode, String message) {
        LinkedHashMap inner = new LinkedHashMap();
        inner.put("id", "5J2xfSA9Y0od4FYRhID0-xYQ4e7-ERERER_AWAWAW_w");
        inner.put("profile_image", "https://phinf.pstatic.net/contact/19201123_185/12345678904026F1N8_PNG/avatar_profile.png");
        inner.put("gender", "M");
        inner.put("email", "email1234@naver.com");
        inner.put("name", "김재원");

        Map attributes = Map.of(
            "resultcode", resultCode,
            "message", message,
            "response", inner
        );
        return new DefaultOAuth2User(
            Collections.emptyList(),
            attributes,
            nameAttributeKey
        );
    }
}
