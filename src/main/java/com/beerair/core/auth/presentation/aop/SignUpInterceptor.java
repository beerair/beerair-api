package com.beerair.core.auth.presentation.aop;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.auth.presentation.filter.GetAuthenticationStrategy;
import com.beerair.core.error.exception.member.MemberNotFoundException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SignUpInterceptor implements HandlerInterceptor {
    private final GetAuthenticationStrategy getAuthenticationStrategy;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final MemberRepository memberRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        var authentication = getAuthenticationStrategy.get(request);
        var synced = newSyncedAuthentication(authentication);
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, synced);
    }

    private AuthTokenAuthentication newSyncedAuthentication(Authentication authentication) {
        LoggedInMember old = (LoggedInMember) authentication.getPrincipal();
        var member = memberRepository.findById(old.getId())
                .orElseThrow(MemberNotFoundException::new);

        return AuthTokenAuthentication.from(
                LoggedInMember.from(member),
                authorities(member),
                null
        );
    }

    private Set<GrantedAuthority> authorities(Member member) {
        return member.getRole()
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
