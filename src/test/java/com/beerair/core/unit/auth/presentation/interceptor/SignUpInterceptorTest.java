package com.beerair.core.unit.auth.presentation.interceptor;

import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.presentation.aop.SignUpInterceptor;
import com.beerair.core.auth.presentation.filter.GetAuthenticationStrategy;
import com.beerair.core.auth.presentation.loginhandler.TokenDelivery;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.infrastructure.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignUpInterceptorTest {
    @InjectMocks
    private SignUpInterceptor signUpInterceptor;
    @Mock
    private GetAuthenticationStrategy getAuthenticationStrategy;
    @Mock
    private AuthTokenCrypto authTokenCrypto;
    @Mock
    private TokenDelivery tokenDelivery;
    @Mock
    private MemberRepository memberRepository;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @DisplayName("회원 가입이 완료 되면, 새로운 토큰을 발급해 쿠키에 추가한다.")
    @Test
    void postHandle() {
        stubbingByNewSyncedAuthentication();
        request.setMethod(HttpMethod.POST.name());

        signUpInterceptor.postHandle(request, response, this, null);

        verify(tokenDelivery, times(1))
                .deliver(
                        any(HttpServletRequest.class),
                        any(HttpServletResponse.class),
                        any(),
                        any()
                );
    }

    private void stubbingByNewSyncedAuthentication() {
        final String MEMBER_ID = "M";

        when(getAuthenticationStrategy.get(any()))
                .thenReturn(authentication);

        var loggedInMember = LoggedInMember.builder()
                .id(MEMBER_ID)
                .build();
        when(authentication.getPrincipal())
                .thenReturn(loggedInMember);

        var member = mock(Member.class);
        when(member.getRole())
                .thenReturn(Role.MEMBER);
        when(memberRepository.findById(MEMBER_ID))
                .thenReturn(Optional.of(member));
    }
}
