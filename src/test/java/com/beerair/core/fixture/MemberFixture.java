package com.beerair.core.fixture;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberFixture {
    public static Fixture<Member> createSocialMemberFixture() {
        var instance = Member.socialBuilder()
                .email("username")
                .profileUrl("https://img.com")
                .socialId("1234")
                .socialType(SocialType.KAKAO)
                .build();
        return new Fixture<>(instance);
    }
}
