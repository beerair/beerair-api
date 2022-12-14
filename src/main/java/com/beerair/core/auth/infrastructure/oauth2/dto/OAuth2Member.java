package com.beerair.core.auth.infrastructure.oauth2.dto;

import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInMember;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class OAuth2Member extends LoggedInMember implements OAuth2User {
    private final Map<String, Object> attributes;
    private final Set<GrantedAuthority> authorities;

    public OAuth2Member(String id, String email, Set<GrantedAuthority> authorities, Map<String, Object> attributes) {
        super(id, email);
        this.attributes = attributes;
        this.authorities = authorities;
    }

    public static OAuth2Member of(Member member, Map<String, Object> attributes) {
        return new OAuth2Member(
                member.getId(),
                member.getEmail(),
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
