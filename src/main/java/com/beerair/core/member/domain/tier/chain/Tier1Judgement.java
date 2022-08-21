package com.beerair.core.member.domain.tier.chain;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;

public class Tier1Judgement extends TierJudgementChain {
    @Override
    public int judge(Level level) {
        if (level.getExp() < 100) {
            return 1;
        }
        return next(level);
    }
}
