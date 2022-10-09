package com.beerair.core.fixture;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.domain.vo.SocialType;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberFixture {
    public static Fixture<Member> createSocialMemberFixture() {
        var random = new Random();
        var member = Member.socialBuilder()
                .email(random.nextInt() + "")
                .phoneNumber(random.nextInt() + "")
                .profileUrl("https://img.com")
                .social(new MemberSocial(new Random().nextInt() + "", SocialType.KAKAO))
                .build();
        return new Fixture<>(member);
    }
}
