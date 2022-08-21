package com.beerair.core.member.dto.response;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberMeResponse {
    private String email;
    private String profileUrl;
    private String nickname;
    private LevelResponse level;

    public MemberMeResponse(Member member, Level level) {
        this.email = member.getEmail();
        this.profileUrl = member.getProfileUrl();
        this.nickname = member.getNickname();
        this.level = new LevelResponse(level);
    }

    @Getter
    private static class LevelResponse {
        private String imageUrl;
        private Integer exp;
        private Integer tier;

        public LevelResponse(Level level) {
            this.imageUrl = level.getImageUrl();
            this.exp = level.getExp();
            this.tier = level.getTier();
        }
    }
}
