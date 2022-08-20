package com.beerair.core.acceptance.auth;

import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.fixture.MemberFixture;
import com.beerair.core.fixture.fake.FakeAuthTokenCrypto;
import com.beerair.core.fixture.fake.FakeOAuth2UserService;
import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

@ScenarioScope
public class AuthStepGivenDefs {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Given("memberId: {string}, access: {string}, refresh: {string} 토큰이 발급 되어있다.")
    public void registerRefreshToken(String memberId, String access, String refresh) {
        var member = MemberFixture.createSocialMemberFixture()
                .set("id", memberId)
                .get();
        FakeOAuth2UserService.setNextMember(member);

        FakeAuthTokenCrypto.register(access, member);
        FakeAuthTokenCrypto.register(refresh, member);

        redisTemplate.opsForValue().set("authToken:" + memberId, access);
        refreshTokenRepository.save(new RefreshToken(memberId, refresh));
    }

    @Given("Access Token 사용 : {string}")
    public void setAccessToken(String access) {
        AccessTokenHolder.access = access;
    }
}
