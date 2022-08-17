package com.beerair.core.member.domain.vo;

import com.beerair.core.common.domain.StringFieldCryptConverter;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class MemberDetails {
    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("닉네임")
    @Column(length = 50, unique = true)
    private String nickname;

    @Comment("레벨 Id")
    private Long leverId;

    @Comment("경험치")
    private Integer exp;

    protected MemberDetails() {
    }

    @Builder
    private MemberDetails(String nickname, Long leverId, Integer exp) {
        this.nickname = nickname;
        this.leverId = leverId;
        this.exp = exp;
    }
}
