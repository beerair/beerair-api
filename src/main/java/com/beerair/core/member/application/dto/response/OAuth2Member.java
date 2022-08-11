package com.beerair.core.member.application.dto.response;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.SocialType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OAuth2Member implements OAuth2User {
    private SocialType socialType;
    private String socialId;
    private String email;
    private String profile;
    private Map<String, Object> attributes;
    private Set<GrantedAuthority> authorities;

    public static OAuth2Member of(Member member) {
        return OAuth2Member.builder()
            .socialType(member.getSocialType())
            .socialId(member.getSociaiId())
            .email(member.getEmail())
            .profile(member.getProfileUrl())
            .build();
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
