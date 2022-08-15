package com.beerair.core.fixture.fake;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.domain.Member;
import lombok.Setter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Collections;

public class FakeOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Setter
    private static Member nextMember;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return OAuth2Member.of(nextMember, Collections.emptyMap());
    }
}
