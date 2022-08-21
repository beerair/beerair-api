package com.beerair.core.member.dto.response;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberMeResponse {
    private String email;
    private String profileUrl;
    private String nickname;
    private Integer tier;

    public MemberMeResponse(Member member, Integer tier) {
        this.email = member.getEmail();
        this.profileUrl = member.getProfileUrl();
        this.nickname = member.getNickname();
        this.tier = tier;
    }
}
