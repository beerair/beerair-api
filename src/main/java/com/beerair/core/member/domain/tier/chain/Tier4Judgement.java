package com.beerair.core.member.domain.tier.chain;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;

public class Tier4Judgement extends TierJudgementChain {
    @Override
    public int judge(Level level) {
        if (level.getExp() >= 1200 && level.getExp() < 2000) {
            return 4;
        }
        return next(level);
    }
}
