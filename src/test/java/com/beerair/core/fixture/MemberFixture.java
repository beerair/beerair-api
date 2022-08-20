package com.beerair.core.fixture;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberFixture {
    public static Fixture<Member> createSocialMemberFixture() {
        var member = Member.socialBuilder()
                .email("username")
                .phoneNumber("01012345678")
                .profileUrl("https://img.com")
                .social(new MemberSocial("1234", SocialType.KAKAO))
                .build();
        return new Fixture<>(member);
    }
}
