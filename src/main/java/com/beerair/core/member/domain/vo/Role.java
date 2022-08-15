package com.beerair.core.member.domain.vo;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {
    USER(Set.of("ROLE_USER")),
    MEMBER(Set.of("ROLE_MEMBER"));

    private final Set<String> authorities;

    Role(Set<String> authorities) {
        this.authorities = authorities;
    }
}
