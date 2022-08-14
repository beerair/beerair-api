package com.beerair.core.member.domain.vo;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public enum Role {
    USER(Set.of(
        new SimpleGrantedAuthority("ROLE_USER"))
    ),
    MEMBER(Set.of(
        new SimpleGrantedAuthority("ROLE_MEMBER"))
    );

    private final Set<GrantedAuthority> authorities;

    Role(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
