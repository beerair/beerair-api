package com.beerair.core.acceptance.auth;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.fixture.MemberFixture;
import com.beerair.core.fixture.fake.FakeAuthTokenCrypto;
import com.beerair.core.fixture.fake.FakeOAuth2UserService;
import com.beerair.core.member.infrastructure.MemberRepository;
import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@ScenarioScope
public class AuthStepGivenDefs {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AuthTokenService authTokenService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Given("access: {string} 토큰이 발급 되어있다.")
    public void 소셜_유저의_토큰이_발급_되어있다(String access) {
        var member = MemberFixture.createSocialMemberFixture().get();
        FakeOAuth2UserService.setNextMember(member);
        memberRepository.save(member);

        FakeAuthTokenCrypto.register(access, member);
    }

    @Transactional
    @Given("access: {string}, refresh: {string} 회원가입된 유저의 토큰이 발급 되어있다.")
    public void 회원가입된_유저의_토큰이_발급_되어있다(String access, String refresh) {
        var member = MemberFixture.createSocialMemberFixture().get();
        member.sign(new Random().nextInt() + "", 1);
        FakeOAuth2UserService.setNextMember(member);
        memberRepository.save(member);

        FakeAuthTokenCrypto.register(access, member);
        FakeAuthTokenCrypto.register(refresh, member);

        authTokenService.issueRefreshToken(
                member.getId(),
                new AuthToken(refresh, new Date(new Date().getTime() + 10000))
        );
    }

    @Given("Access Token 사용 : {string}")
    public void setAccessToken(String access) {
        AccessTokenHolder.access = access;
    }

    @Given("Access Token 미사용")
    public void setNullAccessToken(String access) {
        AccessTokenHolder.access = null;
    }
}
