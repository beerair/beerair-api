package com.beerair.core.auth.infrastructure;

import java.util.Map;
import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.beerair.core.auth.domain.OAuth2Attributes;
import com.beerair.core.member.domain.vo.SocialType;

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
            .socialType(SocialType.NAVER)
            .id(responseAttributes.get("id"))
            .name(responseAttributes.get("name"))
            .profile(responseAttributes.get("profile_image"))
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

    @UtilityClass
    private class Key {
        private static final String RESULT_CODE = "resultcode";
        private static final String MESSAGE = "message";
        private static final String RESPONSE = "response";
    }
}
