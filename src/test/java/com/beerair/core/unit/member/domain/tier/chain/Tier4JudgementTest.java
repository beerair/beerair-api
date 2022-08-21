package com.beerair.core.unit.member.domain.tier.chain;

import com.beerair.core.fixture.Fixture;
import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.tier.chain.Tier4Judgement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class Tier4JudgementTest {

    @ValueSource(ints = {
            1200, 1999
    })
    @DisplayName("경험치가 1200 이상, 2000 미만일때 레벨 4, [Level 객체만 판단]")
    @ParameterizedTest
    void judge(int exp) {
        Level level = new Fixture<Level>(Level.ofDefault())
                .set("exp", exp)
                .get();

        assertThat(new Tier4Judgement().judge(level))
                .isEqualTo(4);
    }
}
