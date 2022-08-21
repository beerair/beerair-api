package com.beerair.core.unit.member.domain.tier.chain;

import com.beerair.core.fixture.Fixture;
import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.tier.chain.Tier2Judgement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class Tier2JudgementTest {

    @ValueSource(ints = {
            100, 499
    })
    @DisplayName("경험치가 100 이상, 500 미만일때 레벨 2, [Level 객체만 판단]")
    @ParameterizedTest
    void judge(int exp) {
        Level level = new Fixture<Level>(Level.ofDefault())
                .set("exp", exp)
                .get();

        assertThat(new Tier2Judgement().judge(level))
                .isEqualTo(2);
    }
}
