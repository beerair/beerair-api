package com.beerair.core.member.domain.vo;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class MemberDetails {
    @Comment("닉네임")
    @Column(length = 50, unique = true)
    private String nickname;

    @Comment("핸드폰 번호")
    @Column(length = 15, unique = true)
    private String phoneNumber;

    @Comment("레벨 Id")
    private Long leverId;

    @Comment("경험치")
    private Integer exp;

    protected MemberDetails() {
    }

    @Builder
    private MemberDetails(String nickname, String phoneNumber, Long leverId, Integer exp) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.leverId = leverId;
        this.exp = exp;
    }
}
