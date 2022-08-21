package com.beerair.core.member.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import com.beerair.core.member.domain.tier.TierJudgement;
import lombok.AccessLevel;
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
    private String id;

    private String imageUrl;

    private Integer exp;

    private Integer tier;

    protected Level() {
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Level(String imageUrl, Integer exp, Integer tier) {
        this.id = IdGenerator.createUUID();
        this.imageUrl = imageUrl;
        this.exp = exp;
        this.tier = tier;
    }

    public static Level ofDefault() {
        return Level.builder()
                .tier(1)
                .exp(0)
                .imageUrl("??")
                .build();
    }

    public void addExp(int exp, TierJudgement judgement) {
        // TODO :: Domain Event 로 받기
        this.exp += exp;
        this.tier = judgement.judge(this);
    }

    public void delete() {
        super.delete();
    }
}
