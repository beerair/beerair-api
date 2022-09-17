package com.beerair.core.cucumber.cleanup;

import com.beerair.core.cucumber.auth.AccessTokenHolder;
import com.beerair.core.fixture.fake.FakeAuthTokenCrypto;
import com.beerair.core.fixture.fake.FakeOAuth2UserService;
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
