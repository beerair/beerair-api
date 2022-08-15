package com.beerair.core.acceptance.auth;

import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.beerair.core.auth.domain.AuthTokenProvider;
import com.beerair.core.auth.domain.RefreshToken;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.RefreshTokenRepository;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2UserServiceImpl;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.fixture.MemberFixture;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.Role;

import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;

@ExtendWith(MockitoExtension.class)
@ScenarioScope
public class AuthStepGivenDefs {
    @Autowired
    private AuthStepClient authStepClient;
    @MockBean
    private OAuth2UserServiceImpl oAuth2UserService;

    @Given("{string} 로그인을 요청하면")
    public void registerRefreshToken(String registration) {
        var member = MemberFixture.createMemberFixture().get();
        var oAuth2Member = OAuth2Member.of(member, Collections.emptyMap());
        when(oAuth2UserService.loadUser(any()))
            .thenReturn(oAuth2Member);

        authStepClient.loginByOAuth2(registration);
    }
}
