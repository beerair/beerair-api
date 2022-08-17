package com.beerair.core.fixture;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberFixture {
    public static Fixture<Member> createSocialMemberFixture() {
        var social = MemberSocial.builder()
                .email("username")
                .phoneNumber("01012345678")
                .profileUrl("https://img.com")
                .socialId("1234")
                .socialType(SocialType.KAKAO)
                .build();
        return new Fixture<>(
                Member.ofSocial(social)
        );
    }
}
