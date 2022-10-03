package com.beerair.core.beer.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.KeyGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.beerair.core.common.util.KeyGenerator.UUID_LENGTH;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "UNIQUE_MEMBER_BEER", columnNames = {"memberId", "beerId"})
        }
)
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeerLike extends BaseEntity {
    @Id
    @Column(length = UUID_LENGTH)
    private String id;

    @Comment("사용자 ID")
    @Column(nullable = false, length = UUID_LENGTH)
    private String memberId;

    @Comment("맥주 ID")
    @Column(nullable = false, length = UUID_LENGTH)
    private String beerId;

    public BeerLike(String memberId, String beerId) {
        this.id = KeyGenerator.createKeyByUUID();
        this.memberId = memberId;
        this.beerId = beerId;
    }
}
