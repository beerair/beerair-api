package com.beerair.core.member.domain.tier.chain;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;

public class Tier2Judgement extends TierJudgementChain {
    @Override
    public int judge(Level level) {
        if (level.getExp() >= 100 && level.getExp() < 500) {
            return 2;
        }
        return next(level);
    }
}
