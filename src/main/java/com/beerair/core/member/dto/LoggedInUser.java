package com.beerair.core.member.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class LoggedInUser {
    private String id;
    private String nickname;
    private String email;

    protected LoggedInUser() {
    }

    @Builder
    protected LoggedInUser(String id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
