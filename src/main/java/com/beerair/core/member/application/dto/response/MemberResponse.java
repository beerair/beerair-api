package com.beerair.core.member.application.dto.response;

import javax.persistence.Id;

import org.hibernate.annotations.Comment;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class MemberResponse {
    private String id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String profileUrl;
    private Role role;
    private String sociaiId;
    private SocialType socialType;
    private Integer exp;
    private Long leverId;

    public static MemberResponse ofSign(Member member) {
        return MemberResponse.builder()
            .socialType(member.getSocialType())

            .build();
    }
}
