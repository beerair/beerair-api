package com.beerair.core.beer.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.beerair.core.common.util.IdGenerator.UUID_LENGTH;

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

    @Builder
    private BeerLike(String memberId, String beerId) {
        this.id = IdGenerator.createUUID();
        this.memberId = memberId;
        this.beerId = beerId;
    }
}
