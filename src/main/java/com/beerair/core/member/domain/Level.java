package com.beerair.core.member.domain;

import com.beerair.core.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Level extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;

    private Integer exp;

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
