package com.beerair.core.auth.infrastructure.oauth2;

import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final OAuth2AttributesLoader attributeLoader;
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2Attributes attributes = attributeLoader.load(request);
        return OAuth2Member.of(member(attributes), attributes.getAttributes());
    }

    private Member member(OAuth2Attributes attributes) {
        Optional<Member> member = memberRepository.findByEmail(attributes.getEmail());
        if (member.isEmpty()) {
            return signByOAuth2(attributes);
        }
        return member.get();
    }

    private Member signByOAuth2(OAuth2Attributes attributes) {
        Member member = Member.builder()
            .role(Role.USER)
            .sociaiId(attributes.getSocialId())
            .socialType(attributes.getSocialType())
            .profileUrl(attributes.getProfile())
            .email(attributes.getEmail())
            .build();
        return memberRepository.save(member);
    }
}
