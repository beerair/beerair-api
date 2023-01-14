package com.beerair.core.config.security;

import com.beerair.core.auth.presentation.filter.AuthTokenAuthenticationFilter;
import com.beerair.core.member.domain.vo.Role;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AuthTokenAuthenticationFilter authTokenAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

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
                .cors().configurationSource(corsConfigurationSource)
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private void configureOAuth2(HttpSecurity http) throws Exception {
        // /oauth2/authorization/naver
        http.oauth2Login()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .userInfoEndpoint()
                .userService(oAuth2UserService);
    }

    private void configureAuthorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll();
    }

    private String createAccess(Collection<String> authorities) {
        var joined = authorities.stream()
                .map(each -> "'" + each + "'")
                .collect(Collectors.joining());
        return "hasAnyAuthority(" + joined + ")";
    }

    private void configureFilters(HttpSecurity http) {
        http.addFilterBefore(authTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
