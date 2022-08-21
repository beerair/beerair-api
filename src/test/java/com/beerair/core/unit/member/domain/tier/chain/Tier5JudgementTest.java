package com.beerair.core.unit.member.domain.tier.chain;

import com.beerair.core.fixture.Fixture;
import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.tier.chain.Tier5Judgement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class Tier5JudgementTest {

    @ValueSource(ints = {
            2000, 20000
    })
    @DisplayName("경험치가 2000 이상일때 레벨 5, [Level 객체만 판단]")
    @ParameterizedTest
    void judge(int exp) {
        Level level = new Fixture<Level>(Level.ofDefault())
                .set("exp", exp)
                .get();

        assertThat(new Tier5Judgement().judge(level))
                .isEqualTo(5);
    }
}
