package com.beerair.core.member.domain.tier.chain;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.tier.TierJudgement;
import lombok.Setter;

import java.util.Objects;

public abstract class TierJudgementChain implements TierJudgement {
    @Setter
    private TierJudgementChain next;

    protected final int next(Level level) {
        if (Objects.isNull(next)) {
            throw new NoClassDefFoundError("TierJudgementChain 정의 되지 않음");
        }
        return next.judge(level);
    }
}
