package com.beerair.core.unit.auth.application;

import static com.beerair.core.fixture.MemberFixture.createSocialMemberFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beerair.core.auth.application.OAuth2UserServiceImpl;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Attributes;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.SocialType;
import com.beerair.core.member.infrastructure.MemberRepository;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

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
            .thenReturn(createSocialMemberFixture().get());

        oAuth2UserService.loadUser(request);

        verify(memberRepository, times(1))
            .save(any());
    }

    @DisplayName("OAuth2로 등록된 유저가 있다면 해당 유저 정보를 사용한다.")
    @Test
    void loadUserWithLogin() {
        Member member = createSocialMemberFixture().get();
        stubbingGetMember(member);

        OAuth2Member oAuth2Member = oAuth2UserService.loadUser(request);

        assertThat(oAuth2Member.getId())
            .isEqualTo(member.getId());
    }

    private void stubbingGetMember(Member member) {
        OAuth2Attributes attributes = OAuth2Attributes.builder()
                .socialId("1234")
                .socialType(SocialType.KAKAO)
                .phoneNumber("01042721234")
                .email("alawtlaktnakltal@kakao.ggg")
                .profile("https://akltlpzpzl.com/aaaaa")
                .attributes(Collections.emptyMap())
                .build();
        when(oAuth2AttributesLoader.load(any()))
            .thenReturn(attributes);
        when(memberRepository.findBySocial(any()))
            .thenReturn(Optional.ofNullable(member));
    }
}
