package com.beerair.core.unit.auth.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2UserServiceImpl;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.infrastructure.MemberRepository;

@ExtendWith(MockitoExtension.class)
public class OAuth2UserServiceImplTest {
    @Mock
    private OAuth2UserRequest request;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private OAuth2AttributesLoader oAuth2AttributesLoader;
    private OAuth2UserServiceImpl oAuth2UserService;

    @BeforeEach
    void setUp() {
        this.oAuth2UserService = new OAuth2UserServiceImpl(
            oAuth2AttributesLoader,
            memberRepository
        );
    }

    @DisplayName("OAuth2로 처음 로그인 한다면 유저 정보를 새로 등록 한다.")
    @Test
    void loadUserWithSign() {
        stubbingGetMember(null);
        when(memberRepository.save(any()))
            .thenReturn(Member.builder().build());

        oAuth2UserService.loadUser(request);

        verify(memberRepository, times(1))
            .save(any());
    }

    @DisplayName("OAuth2로 등록된 유저가 있다면 해당 유저 정보를 사용한다.")
    @Test
    void loadUserWithLogin() {
        Member member = Member.builder().sociaiId("1234").build();
        stubbingGetMember(member);

        OAuth2Member oAuth2Member = (OAuth2Member) oAuth2UserService.loadUser(request);

        assertThat(oAuth2Member.getSocialId())
            .isEqualTo(member.getSociaiId());
    }

    private void stubbingGetMember(Member member) {
        OAuth2Attributes attributes = OAuth2Attributes.builder().email("1234").build();
        when(oAuth2AttributesLoader.load(any()))
            .thenReturn(attributes);
        when(memberRepository.findByEmail(anyString()))
            .thenReturn(Optional.ofNullable(member));
    }
}
