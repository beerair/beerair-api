package com.beerair.core.member.dto.response;

import com.beerair.core.member.dto.query.MemberDto;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class MemberResponse {
    private String id;
    private String email;
    private String profileUrl;
    private String nickname;
    private Set<String> authorities;

    private Integer tier;
    private String levelImage;

    public static MemberResponse from(MemberDto dto) {
        MemberDto.MemberInfo m = dto.getMember();
        MemberDto.LevelInfo l = dto.getLevel();
        return MemberResponse.builder()
            .email(m.getEmail())
            .profileUrl(m.getProfileUrl())
            .nickname(m.getNickname())
            .tier(l.getTier())
            .levelImage(l.getImageUrl())
            .authorities(m.getRole().getAuthorities())
            .build();
    }
}
