package com.beerair.core.auth.infrastructure.oauth2.dto;

import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class OAuth2Member extends LoggedInUser implements OAuth2User {
    private final Map<String, Object> attributes;
    private final Set<GrantedAuthority> authorities;

    private OAuth2Member(String id, String email, String nickname, Set<GrantedAuthority> authorities, Map<String, Object> attributes) {
        super(id, email, nickname);
        this.attributes = attributes;
        this.authorities = authorities;
    }

    public static OAuth2Member of(Member member, Map<String, Object> attributes) {
        return new OAuth2Member(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                createAuthorities(member),
                attributes
        );
    }

    private static Set<GrantedAuthority> createAuthorities(Member member) {
        return member.getRole()
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return getId();
    }
}
