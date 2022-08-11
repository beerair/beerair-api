package com.beerair.core.auth.infrastructure;

import java.util.Map;
import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import com.beerair.core.auth.application.OAuth2Attributes;
import com.beerair.core.auth.domain.UserGender;
import com.beerair.core.auth.domain.UserSocialType;

import lombok.experimental.UtilityClass;

public final class NaverOAuth2AttributesLoader extends DelegateOAuth2AttributesLoader {
    private static final String SUCCESS_RESULT_CODE = "00";
    public static final String REGISTRATION_ID = "naver";

    @Override
    protected boolean isLoadable(OAuth2UserRequest request) {
        return request.getClientRegistration()
                      .getRegistrationId()
                      .equals(REGISTRATION_ID);
    }

    @Override
    protected OAuth2Attributes convert(DefaultOAuth2User user) {
        verify(user);

        Map<String, String> responseAttributes = responseAttributes(user.getAttributes());
        return OAuth2Attributes.builder()
            .socialType(UserSocialType.NAVER)
            .id(responseAttributes.get("id"))
            .name(responseAttributes.get("name"))
            .profile(responseAttributes.get("profile_image"))
            .gender(toGender(responseAttributes.get("gender")))
            .email(responseAttributes.get("email"))
            .build();
    }

    private void verify(DefaultOAuth2User user) {
        var resultCode = resultCode(user);
        if (Objects.nonNull(resultCode) && resultCode.equals(SUCCESS_RESULT_CODE)) {
            return;
        }
        throw new BadCredentialsException(message(user));
    }

    private String resultCode(DefaultOAuth2User user) {
        return user.getAttribute(Key.RESULT_CODE);
    }

    private String message(DefaultOAuth2User user) {
        return user.getAttribute(Key.MESSAGE);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> responseAttributes(Map<String, ?> attributes) {
        return (Map<String, String>) attributes.get(Key.RESPONSE);
    }

    private UserGender toGender(String rawGender) {
        if (rawGender.equals("M")) {
            return UserGender.MAN;
        }
        if (rawGender.equals("W")) {
            return UserGender.WOMAN;
        }
        return UserGender.UNKNOWN;
    }

    @UtilityClass
    private class Key {
        private static final String RESULT_CODE = "resultcode";
        private static final String MESSAGE = "message";
        private static final String RESPONSE = "response";
    }
}
