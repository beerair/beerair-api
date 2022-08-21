package com.beerair.core.member.domain.tier.chain;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;

public class Tier3Judgement extends TierJudgementChain {
    @Override
    public int judge(Level level) {
        if (level.getExp() >= 500 && level.getExp() < 1200) {
            return 3;
        }
        return next(level);
    }
}
