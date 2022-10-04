package com.beerair.core.beer.domain;

import static com.beerair.core.common.util.IdGenerator.UUID_LENGTH;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "UNIQUE_MEMBER_BEER", columnNames = { "memberId", "beerId" })
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
        this.id = IdGenerator.createUUID();
        this.memberId = memberId;
        this.beerId = beerId;
    }
}
