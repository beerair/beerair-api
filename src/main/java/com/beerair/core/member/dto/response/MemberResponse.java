package com.beerair.core.member.dto.response;

import com.beerair.core.member.dto.query.MemberDto;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(
        dataType = "String",
        value = "ID",
        example = "c4328f0675834f8687b17f0718146fa9"
    )
    private String id;

    @ApiModelProperty(
        dataType = "String",
        value = "이메일",
        example = "beerair.official@gmail.com"
    )
    private String email;

    @ApiModelProperty(
        dataType = "String",
        value = "프로필 사진 (Social)",
        example = "https://picsum.photos/200/300"
    )
    private String profileUrl;

    @ApiModelProperty(
        dataType = "String",
        value = "닉네임",
        example = "맥주아저씨"
    )
    private String nickname;

    @ApiModelProperty(
        dataType = "Array[String]",
        value = "권한 정보",
        example = "[\"ROLE_MEMBER\"]"
    )
    private Set<String> authorities;

    @ApiModelProperty(
        dataType = "Number",
        value = "사용자 레벨",
        example = "5"
    )
    private Integer tier;

    @ApiModelProperty(
        dataType = "String",
        value = "사용자 레벨 이미지",
        example = "https://beerair-service.s3.ap-northeast-2.amazonaws.com/MEMBER/LEVEL/5.png"
    )
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
