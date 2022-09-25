package com.beerair.core.unit.member.domain;

import com.beerair.core.error.exception.member.MemberUnableSignException;
import com.beerair.core.fixture.Fixture;
import com.beerair.core.member.domain.Level;
import com.beerair.core.member.domain.Levels;
import com.beerair.core.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.beerair.core.fixture.MemberFixture.createSocialMemberFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MemberTest {
    private Fixture<Member> memberFixture;

    @BeforeEach
    void setUp() {
        memberFixture = createSocialMemberFixture();
        memberFixture.get().sign("NICKNAME", 0);
    }

    @DisplayName("Role : User 일때만 회원 가입 할 수 있다.")
    @Test
    void 회원가입_실패() {
        assertThatThrownBy(() -> memberFixture.get().sign("NICKNAME", 0))
                .isInstanceOf(MemberUnableSignException.class);
    }

    @DisplayName("닉네임 변경")
    @ValueSource(strings = { "1234", "5678", "AAA", "BBB" })
    @ParameterizedTest
    void 닉네임_변경(String expert) {
        var member = memberFixture.get();
        member.changeNickname(expert);

        assertThat(member.getNickname())
                .isEqualTo(expert);
    }

    @DisplayName("탈퇴")
    @Test
    void 탈퇴() {
        var member = memberFixture.get();
        member.resign();

        assertThat(member.isDeleted())
                .isTrue();
    }

    @DisplayName("경험치를 올리고 Level을 결정한다.")
    @Test
    void 경험치_증가() {
        int EXPERT_LEVEL_ID = 1;
        var levels = mock(Levels.class);
        when(levels.getByExp(anyInt()))
            .thenReturn(
                Level.builder()
                    .id(EXPERT_LEVEL_ID)
                    .build()
            );

        var member = memberFixture.get();
        member.increaseExp(levels);

        assertThat(member.getLevelId())
            .isEqualTo(EXPERT_LEVEL_ID);
    }
}
