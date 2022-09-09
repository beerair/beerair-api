package com.beerair.core.member.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.domain.StringFieldCryptConverter;
import com.beerair.core.common.util.IdGenerator;
import com.beerair.core.error.exception.member.MemberUnableSignException;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.domain.vo.Role;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

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

    @Embedded
    private MemberSocial social;

    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("이메일")
    @Column(nullable = false, unique = true)
    private String email;

    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("프로필 이미지")
    @Column(length = 500)
    private String profileUrl;

    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("핸드폰 번호")
    @Column(unique = true)
    private String phoneNumber;

    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("닉네임")
    @Column(length = 50, unique = true, nullable = true)
    private String nickname;

    @Comment("레벨 Id")
    private Integer levelId;

    @Comment("경험치")
    private Integer exp;

    @Enumerated(EnumType.STRING)
    @Comment("권한 정보")
    @Column(length = 20, nullable = false)
    private Role role;

    protected Member() {
    }

    @Builder(builderMethodName = "socialBuilder")
    private Member(MemberSocial social, String email, String profileUrl, String phoneNumber) {
        this.id = IdGenerator.createUUID();
        this.social = social;
        this.email = email;
        this.profileUrl = profileUrl;
        this.phoneNumber = phoneNumber;
        this.role = Role.USER;
    }

    public void sign(String nickname, int levelId) {
        if (role == Role.MEMBER) {
            throw new MemberUnableSignException();
        }
        this.role = Role.MEMBER;
        this.levelId = levelId;
        this.exp = 0;
        changeNickname(nickname);
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void resign() {
        this.delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
