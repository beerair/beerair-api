package com.beerair.core.member.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import com.beerair.core.member.domain.vo.MemberDetails;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.beerair.core.common.util.IdGenerator.UUID_LENGTH;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UNIQUE_SOCIAL",
                        columnNames = {
                                "socialId", "socialType"
                        })
        },
        indexes = {
                @Index(
                        name = "INDEX_EMAIL",
                        columnList = "email"
                )
        }

)
@Getter
@Entity
public class Member extends BaseEntity {
    @Comment("Id")
    @Id
    @Column(length = UUID_LENGTH)
    private String id;

    @Comment("이메일")
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Comment("프로필 이미지")
    @Column(length = 500)
    private String profileUrl;

    @Comment("권한 정보")
    @Column(length = 20, nullable = false)
    private Role role;

    @Comment("소셜 계정 ID")
    @Column(length = 100, nullable = false)
    private String socialId;

    @Comment("소셜 계정 종류")
    @Column(length = 100, nullable = false)
    private SocialType socialType;

    @Embedded
    private MemberDetails details;

    protected Member() {
    }

    @Builder(builderMethodName = "socialBuilder")
    private Member(String email, String profileUrl, String socialId, SocialType socialType) {
        this.id = IdGenerator.createUUID();
        this.email = email;
        this.profileUrl = profileUrl;
        this.socialId = socialId;
        this.socialType = socialType;

        this.role = Role.USER;
        this.details = null;
    }
}
