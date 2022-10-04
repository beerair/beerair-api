package com.beerair.core.unit.auth.infrastructure.oauth2;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beerair.core.auth.infrastructure.oauth2.NaverOAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.fixture.Fixture;
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

    @DisplayName("Naver 소셜 로그인이 아니면 다음 체인으로 으로 넘긴다.")
    @Test
    void isLoadable() {
        var mockNext = mock(OAuth2AttributesLoader.class);
        loader.setNext(mockNext);
        stubbingGetClientRegistration("KAKAO");

        loader.load(request);
        verify(mockNext, times(1)).load(any());
    }

    @DisplayName("DefaultOAuth2User attributes 를 OAuth2Attributes spec 으로 변환한다.")
    @Test
    void convert() {
        DefaultOAuth2UserService stub = mockingDefaultOAuth2UserService();
        var user = createUser("response", "00", "message");
        stubbingGetClientRegistration(NaverOAuth2AttributesLoader.REGISTRATION_ID);
        stubbingLoadUser(stub, user);

        var expert = loader.load(request);
        assertThat(expert.getEmail())
            .isEqualTo("email1234@naver.com");
    }

    private void stubbingGetClientRegistration(String registrationId) {
        ClientRegistration registration = ClientRegistration
            .withRegistrationId(registrationId)
            .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
            .userNameAttributeName("response")
            .build();
        when(request.getClientRegistration())
            .thenReturn(registration);
    }

    private DefaultOAuth2UserService mockingDefaultOAuth2UserService() {
        DefaultOAuth2UserService stub = mock(DefaultOAuth2UserService.class);
        new Fixture<>(loader).set("delegate", stub);
        return stub;
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
            emptyList(),
            attributes,
            nameAttributeKey
        );
    }

    private void stubbingLoadUser(DefaultOAuth2UserService service, OAuth2User user) {
        when(service.loadUser(request))
            .thenReturn(user);
    }

    @DisplayName("result code가 '00'이 아니면 BadCredentialsException 오류를 던진다.")
    @Test
    void convertFail1() {
        DefaultOAuth2UserService stub = mockingDefaultOAuth2UserService();
        var user = createUser("response", "01", "잘못 되었다.");
        stubbingGetClientRegistration(NaverOAuth2AttributesLoader.REGISTRATION_ID);
        stubbingLoadUser(stub, user);

        assertThatThrownBy(() -> loader.load(request))
            .isInstanceOf(BadCredentialsException.class);
    }
}
