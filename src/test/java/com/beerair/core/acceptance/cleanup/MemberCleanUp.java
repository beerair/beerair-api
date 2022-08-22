package com.beerair.core.acceptance.cleanup;

import com.beerair.core.acceptance.auth.AccessTokenHolder;
import com.beerair.core.fixture.fake.FakeAuthTokenCrypto;
import com.beerair.core.fixture.fake.FakeOAuth2UserService;
import com.beerair.core.member.infrastructure.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberCleanUp implements CleanUp {
    @Override
    public void exec() {
        FakeOAuth2UserService.setNextMember(null);
        FakeAuthTokenCrypto.cleanUp();
        AccessTokenHolder.access = null;
    }
}
