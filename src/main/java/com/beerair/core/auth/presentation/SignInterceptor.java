package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.auth.presentation.filter.GetAuthenticationStrategy;
import com.beerair.core.auth.presentation.loginhandler.TokenDelivery;
import com.beerair.core.error.exception.member.MemberNotFoundException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.infrastructure.MemberRepository;
import lombok.Builder;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.stream.Collectors;

public class SignInterceptor implements HandlerInterceptor {
    private final GetAuthenticationStrategy getAuthenticationStrategy;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final TokenDelivery tokenDelivery;
    private final AuthTokenService authTokenService;
    private final MemberRepository memberRepository;

    @Builder
    private SignInterceptor(
            GetAuthenticationStrategy getAuthenticationStrategy,
            AuthTokenCrypto accessTokenCrypto,
            AuthTokenCrypto refreshTokenCrypto,
            TokenDelivery tokenDelivery,
            AuthTokenService authTokenService,
            MemberRepository memberRepository
    ) {
        this.getAuthenticationStrategy = getAuthenticationStrategy;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
        this.tokenDelivery = tokenDelivery;
        this.authTokenService = authTokenService;
        this.memberRepository = memberRepository;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            return;
        }
        var authentication = getAuthenticationStrategy.get(request);
        var synced = newSyncedAuthentication(authentication);
        var access = accessTokenCrypto.encrypt(synced);
        var refresh = refreshTokenCrypto.encrypt(synced);

        authTokenService.issueRefreshToken(synced.getPrincipal().getId(), refresh);
        tokenDelivery.deliver(request, response, access, refresh);
    }

    private AuthTokenAuthentication newSyncedAuthentication(Authentication authentication) {
        var old = (LoggedInMember) authentication.getPrincipal();
        var member = memberRepository.findById(old.getId())
                .orElseThrow(MemberNotFoundException::new);

        return AuthTokenAuthentication.from(
                LoggedInMember.from(member),
                authorities(member)
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
