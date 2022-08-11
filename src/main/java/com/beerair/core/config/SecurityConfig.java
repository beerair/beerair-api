package com.beerair.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.beerair.core.auth.application.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.NaverOAuth2AttributesLoader;
import com.beerair.core.auth.presentation.OAuth2SuccessHandler;
import com.beerair.core.auth.application.OAuth2UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final OAuth2UserServiceImpl oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;

    @Bean
    public OAuth2AttributesLoader oAuth2AttributesLoader() {
        return new NaverOAuth2AttributesLoader();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        configureAuthToken(http);
        configureOAuth2(http);
        configureAuthorizeRequests(http);
        configureFilters(http);
        return http.build();
    }

    private void configureAuthToken(HttpSecurity http) throws Exception {
        http.csrf()
            .and()
            .csrf().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();
        // TODO
    }

    private void configureOAuth2(HttpSecurity http) throws Exception {
        // /oauth2/authorization/naver
        http.oauth2Login()
            .successHandler(successHandler)
            .userInfoEndpoint().userService(oAuth2UserService);
    }

    private void configureAuthorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and();
    }

    private void configureFilters(HttpSecurity http) {
        //http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
