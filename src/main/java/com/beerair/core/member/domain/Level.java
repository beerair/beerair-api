package com.beerair.core.member.domain;

import com.beerair.core.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Level extends BaseEntity {
    @Comment("Level Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("레벨 이미지")
    private String imageUrl;

    @Comment("요구 경험치")
    private Integer exp;

    @Comment("레벨")
    private Integer tier;

    protected Level() {
    }

    @Builder
    private Level(Integer id, String imageUrl, Integer exp, Integer tier) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.exp = exp;
        this.tier = tier;
    }
}
