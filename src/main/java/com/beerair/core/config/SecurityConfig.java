package com.beerair.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.beerair.core.auth.presentation.AuthTokenAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthTokenAuthenticationFilter authTokenAuthenticationFilter;

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
            .successHandler(authenticationSuccessHandler)
            .userInfoEndpoint()
            .userService(oAuth2UserService);
    }

    private void configureAuthorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and();
    }

    private void configureFilters(HttpSecurity http) {
        http.addFilterBefore(authTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
