package com.beerair.core.member.domain.tier.chain;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;

public class Tier5Judgement extends TierJudgementChain {
    @Override
    public int judge(Level level) {
        if (level.getExp() >= 2000) {
            return 5;
        }
        return next(level);
    }
}
