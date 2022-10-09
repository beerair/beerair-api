package com.beerair.core.auth.infrastructure.oauth2;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import com.beerair.core.member.domain.vo.SocialType;
import java.util.Map;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

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
    protected OAuth2Attributes convert(DefaultOAuth2User user, String attributeKey) {
        verify(user);

        Map<String, String> attributes = user.getAttribute(attributeKey);
        Map<String, Object> rawAttributes = user.getAttribute(attributeKey);

        return OAuth2Attributes.builder()
                .socialType(SocialType.NAVER)
                .socialId(attributes.get("id"))
                .profile(attributes.get("profile_image"))
                .email(attributes.get("email"))
                .phoneNumber(attributes.get("mobile"))
                .attributes(rawAttributes)
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

    @UtilityClass
    private class Key {
        private static final String RESULT_CODE = "resultcode";
        private static final String MESSAGE = "message";
    }
}
