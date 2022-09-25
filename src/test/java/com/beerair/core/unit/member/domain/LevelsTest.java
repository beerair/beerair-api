package com.beerair.core.unit.member.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Levels;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LevelsTest {
    private Levels levels;

    @BeforeEach
    void setUp() {
        List<Level> samples = List.of(
            Level.builder()
                .id(1)
                .tier(1)
                .exp(1)
                .build(),
            Level.builder()
                .id(2)
                .tier(2)
                .exp(1)
                .build()
        );
        levels = new Levels(samples);
    }

    @DisplayName("높은 티어를 기준으로 삼는다.")
    @Test
    void getByExpTest() {
        final int EXP = 1;

        assertThat(levels.getByExp(EXP).getId())
            .isEqualTo(2);
    }
}
