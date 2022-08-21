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
    private String levelImage;

    protected MemberMeResponse() {
    }

    public MemberMeResponse(Member member, Level level) {
        this.email = member.getEmail();
        this.profileUrl = member.getProfileUrl();
        this.nickname = member.getNickname();
        this.tier = level.getTier();
        this.levelImage = level.getImageUrl();
    }
}
