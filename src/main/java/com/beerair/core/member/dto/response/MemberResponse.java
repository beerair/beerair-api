package com.beerair.core.member.dto.response;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
public class MemberResponse {
    private String id;
    private String email;
    private String profileUrl;
    private String nickname;

    private Integer tier;
    private String levelImage;

    protected MemberResponse() {
    }

    public MemberResponse(Member member, Level level) {
        this.email = member.getEmail();
        this.profileUrl = member.getProfileUrl();
        this.nickname = member.getNickname();
        this.tier = level.getTier();
        this.levelImage = level.getImageUrl();
    }
}