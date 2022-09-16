package com.beerair.core.config.web;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.TokenPurpose;
import com.beerair.core.auth.presentation.aop.AuthMemberArgumentResolver;
import com.beerair.core.auth.presentation.aop.ReSignUpInterceptor;
import com.beerair.core.auth.presentation.aop.SignInterceptor;
import com.beerair.core.auth.presentation.filter.GetAuthenticationStrategy;
import com.beerair.core.auth.presentation.loginhandler.TokenDelivery;
import com.beerair.core.member.infrastructure.MemberRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final GetAuthenticationStrategy getAuthenticationStrategy;
    private final AuthTokenCrypto accessTokenCrypto;
    private final AuthTokenCrypto refreshTokenCrypto;
    private final TokenDelivery tokenDelivery;
    private final MemberRepository memberRepository;
    private final AuthTokenService authTokenService;

    public WebMvcConfig(GetAuthenticationStrategy getAuthenticationStrategy,
                        @Qualifier(TokenPurpose.ACCESS) AuthTokenCrypto accessTokenCrypto,
                        @Qualifier(TokenPurpose.REFRESH) AuthTokenCrypto refreshTokenCrypto,
                        TokenDelivery tokenDelivery,
                        MemberRepository memberRepository,
                        AuthTokenService authTokenService) {
        this.getAuthenticationStrategy = getAuthenticationStrategy;
        this.accessTokenCrypto = accessTokenCrypto;
        this.refreshTokenCrypto = refreshTokenCrypto;
        this.tokenDelivery = tokenDelivery;
        this.memberRepository = memberRepository;
        this.authTokenService = authTokenService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberArgumentResolver());
    }

    @Bean
    public AuthMemberArgumentResolver memberArgumentResolver() {
        return new AuthMemberArgumentResolver(getAuthenticationStrategy);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInterceptor())
                .addPathPatterns("/api/v1/members");

        registry.addInterceptor(reSignUpInterceptor())
                .addPathPatterns("/api/v1/members");
    }

    @Bean
    public SignInterceptor signInterceptor() {
        return SignInterceptor.builder()
                .tokenDelivery(tokenDelivery)
                .getAuthenticationStrategy(getAuthenticationStrategy)
                .accessTokenCrypto(accessTokenCrypto)
                .refreshTokenCrypto(refreshTokenCrypto)
                .tokenDelivery(tokenDelivery)
                .memberRepository(memberRepository)
                .authTokenService(authTokenService)
                .build();
    }

    @Bean
    public ReSignUpInterceptor reSignUpInterceptor() {
        return new ReSignUpInterceptor();
    }
}
