package com.beerair.core.auth.application;

import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.common.util.ValidateUtil;
import com.beerair.core.error.exception.auth.BadLoginRequestException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

@Transactional
@RequiredArgsConstructor
@Service
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final OAuth2AttributesLoader attributeLoader;
    private final MemberRepository memberRepository;

    @Override
    public OAuth2Member loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        var attributes = attributeLoader.load(request);
        return OAuth2Member.of(member(attributes), attributes.getAttributes());
    }

    private Member member(OAuth2Attributes attributes) {
        verify(attributes);

        var social = new MemberSocial(attributes.getSocialId(), attributes.getSocialType());

        return memberRepository
                .findBySocial(social)
                .orElseGet(() -> signByOAuth2(attributes, social));
    }

    private void verify(OAuth2Attributes attributes) {
        try {
            ValidateUtil.validate(attributes);
        } catch (ConstraintViolationException e) {
            var firstViolationMessage = e.getConstraintViolations()
                    .iterator().next()
                    .getMessage();

            throw new BadLoginRequestException(firstViolationMessage);
        }
    }

    private Member signByOAuth2(OAuth2Attributes attributes, MemberSocial social) {
        var member = Member.socialBuilder()
                .email(attributes.getEmail())
                .phoneNumber(attributes.getPhoneNumber())
                .profileUrl(attributes.getProfile())
                .social(social)
                .build();

        return memberRepository.save(member);
    }
}
