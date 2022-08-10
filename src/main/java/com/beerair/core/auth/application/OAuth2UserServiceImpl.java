package com.beerair.core.auth.application;

import java.util.List;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final List<OAuth2AttributesLoader> attributeLoaders;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2Attributes attributes = loadAttributes(request);
        assert(attributes != null);
        return null;
    }

    private OAuth2Attributes loadAttributes(OAuth2UserRequest request) {
        return attributesLoader(request).load(request);
    }

    private OAuth2AttributesLoader attributesLoader(OAuth2UserRequest request) {
        return attributeLoaders
            .stream()
            .filter(eachLoader -> eachLoader.isLoadable(request))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 소셜 로그인 입니다."));
    }
}
