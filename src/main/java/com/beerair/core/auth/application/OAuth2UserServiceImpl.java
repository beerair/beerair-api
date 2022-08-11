package com.beerair.core.auth.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.beerair.core.auth.domain.OAuth2Attributes;
import com.beerair.core.member.application.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final OAuth2AttributesLoader attributeLoader;
    private final UserDetailsService userDetailsService;
    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2Attributes attributes = attributeLoader.load(request);
        assert(attributes != null);
        return null;
    }
}
