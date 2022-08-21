package com.beerair.core.config.bean;

import com.beerair.core.member.domain.tier.TierJudgement;
import com.beerair.core.member.domain.tier.chain.Tier1Judgement;
import com.beerair.core.member.domain.tier.chain.Tier2Judgement;
import com.beerair.core.member.domain.tier.chain.Tier3Judgement;
import com.beerair.core.member.domain.tier.chain.Tier4Judgement;
import com.beerair.core.member.domain.tier.chain.Tier5Judgement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberBeanConfig {
    @Bean
    public TierJudgement tierJudgement() {
        var tier1Judgement = new Tier1Judgement();
        var tier2Judgement = new Tier2Judgement();
        var tier3Judgement = new Tier3Judgement();
        var tier4Judgement = new Tier4Judgement();
        var tier5Judgement = new Tier5Judgement();

        tier1Judgement.setNext(tier2Judgement);
        tier2Judgement.setNext(tier3Judgement);
        tier3Judgement.setNext(tier4Judgement);
        tier4Judgement.setNext(tier5Judgement);
        return tier1Judgement;
    }
}
