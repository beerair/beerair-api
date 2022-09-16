package com.beerair.core.member.dto;

import com.beerair.core.member.domain.Member;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class LoggedInMember {
    private String id;
    private String email;

    protected LoggedInMember() {
    }

    @Builder
    protected LoggedInMember(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public static LoggedInMember from(Member member) {
        return LoggedInMember.builder()
                .id(member.getId())
                .email(member.getEmail())
                .build();
    }
}
