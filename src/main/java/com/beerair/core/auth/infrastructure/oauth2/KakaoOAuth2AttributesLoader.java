package com.beerair.core.auth.infrastructure.oauth2;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import com.beerair.core.member.domain.vo.SocialType;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Map;
import java.util.Objects;

public final class KakaoOAuth2AttributesLoader extends DelegateOAuth2AttributesLoader {
    private static final String ACCOUNT_DETAILS_KEY = "kakao_account";
    public static final String REGISTRATION_ID = "kakao";

    @Override
    protected boolean isLoadable(OAuth2UserRequest request) {
        return request.getClientRegistration()
                .getRegistrationId()
                .equals(REGISTRATION_ID);
    }

    @Override
    protected OAuth2Attributes convert(DefaultOAuth2User user, String attributeKey) {
        Map<String, Object> attributes = user.getAttributes();
        Map<String, Object> details = details(attributes);

        return OAuth2Attributes.builder()
                .socialType(SocialType.KAKAO)
                .socialId(attributes.get(attributeKey).toString())
                .profile(profile(details))
                .email(email(details))
                .attributes(attributes)
                .build();
    }

    private Map<String, Object> details(Map<String, Object> attributes) {
        return (Map<String, Object>) attributes.get(ACCOUNT_DETAILS_KEY);
    }

    private String profile(Map<String, Object> details) {
        var profile = (Map<String, String>) details.get("profile");
        if (Objects.isNull(profile)) {
            return null;
        }
        return profile.get("profile_image");
    }

    private String email(Map<String, Object> details) {
        return (String) details.get("email");
    }
}
