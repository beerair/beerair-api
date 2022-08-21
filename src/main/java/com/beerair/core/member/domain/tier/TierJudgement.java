package com.beerair.core.member.domain.tier;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;

public interface TierJudgement {
    int judge(Level level);
}
