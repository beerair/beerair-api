package com.beerair.core.auth.infrastructure.oauth2.dto;

import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.SocialType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OAuth2Member implements OAuth2User {
    private String id;
    private SocialType socialType;
    private String socialId;
    private String email;
    private String profile;
    private Map<String, Object> attributes;
    private Set<GrantedAuthority> authorities;

    public static OAuth2Member of(Member member, Map<String, Object> attributes) {
        return OAuth2Member.builder()
                .id(member.getId())
                .socialType(member.getSocialType())
                .socialId(member.getSociaiId())
                .email(member.getEmail())
                .profile(member.getProfileUrl())
                .authorities(authorities(member))
                .attributes(attributes)
                .build();
    }

    private static Set<GrantedAuthority> authorities(Member member) {
        return member.getRole()
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toSet());
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
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
