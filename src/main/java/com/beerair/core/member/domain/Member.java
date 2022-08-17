package com.beerair.core.member.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import com.beerair.core.member.domain.vo.MemberDetails;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.domain.vo.Role;
import lombok.AccessLevel;
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

    @Embedded
    private MemberDetails details;

    @Comment("권한 정보")
    @Column(length = 20, nullable = false)
    private Role role;

    protected Member() {
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Member(MemberSocial social, MemberDetails details, Role role) {
        this.id = IdGenerator.createUUID();
        this.social = social;
        this.details = details;
        this.role = role;
    }

    public static Member ofSocial(MemberSocial social) {
        return Member.builder()
                .social(social)
                .role(Role.USER)
                .build();
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
