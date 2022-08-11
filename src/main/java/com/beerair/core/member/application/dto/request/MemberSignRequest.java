package com.beerair.core.member.application.dto.request;

import javax.persistence.Id;

import org.hibernate.annotations.Comment;

import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberSignRequest {
    private String email;

    private String name;

    private String nickname;

    private String phoneNumber;

    private String profileUrl;

    private Role role;

    private String sociaiId;

    private SocialType socialType;

    private Integer exp;

    private Long leverId;
}
