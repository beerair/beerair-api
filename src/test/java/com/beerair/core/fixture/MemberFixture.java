package com.beerair.core.fixture;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberFixture {
    public static Fixture<Member> createMemberFixture() {
        var instance = Member.builder()
            .email("username")
            .exp(0)
            .leverId(0L)
            .nickname("nickname")
            .phoneNumber("010-1234-5678")
            .profileUrl("https://img.com")
            .sociaiId("KAKAO")
            .socialType(SocialType.KAKAO)
            .role(Role.MEMBER)
            .build();
        return new Fixture<>(instance);
    }
}
