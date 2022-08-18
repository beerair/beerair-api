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
import lombok.extern.slf4j.Slf4j;
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
        OAuth2Attributes attributes = attributeLoader.load(request);
        return OAuth2Member.of(member(attributes), attributes.getAttributes());
    }

    private Member member(OAuth2Attributes attributes) {
        verify(attributes);

        MemberSocial social = createSocial(attributes);
        return memberRepository
                .findBySocial(social)
                .orElseGet(() -> signByOAuth2(social));
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

    private MemberSocial createSocial(OAuth2Attributes attributes) {
        return MemberSocial.builder()
                .email(attributes.getEmail())
                .phoneNumber(attributes.getPhoneNumber())
                .profileUrl(attributes.getProfile())
                .socialId(attributes.getSocialId())
                .socialType(attributes.getSocialType())
                .build();
    }

    private Member signByOAuth2(MemberSocial social) {
        return memberRepository.save(
                Member.ofSocial(social)
        );
    }
}
