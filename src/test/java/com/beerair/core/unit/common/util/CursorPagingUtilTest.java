package com.beerair.core.unit.common.util;

import com.beerair.core.common.dto.CursorPageDto;
import com.beerair.core.common.util.CursorPagingUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CursorPagingUtilTest {
    @DisplayName("hasNext는 다음 항목 페이징이 가능한지 여부이다.")
    @Test
    void hasNext() {
        CursorPageDto<Integer, Integer> sut;

        sut = CursorPagingUtil.paging(this::createDummyResult, this::getDummyKeyFunction, 20);
        assertThat(sut.getHasNext()).isTrue();

        sut = CursorPagingUtil.paging(pageable -> createDummyResult(19), this::getDummyKeyFunction, 20);
        assertThat(sut.getHasNext()).isFalse();
    }

    @DisplayName("다음 Cursor를 자동으로 가져온다. 다음 Cursor는 Data에 미포함된다.")
    @Test
    void nextCursorT() {
        CursorPageDto<Integer, Integer> sut = CursorPagingUtil.paging(this::createDummyResult, this::getDummyKeyFunction, 20);
        List<Integer> keys = sut.getData().stream()
                .map(this::getDummyKeyFunction)
                .collect(Collectors.toList());

        assertThat(sut.getNextCursor()).isNotNull();
        assertThat(keys).doesNotContain(sut.getNextCursor());
    }

    List<Integer> createDummyResult(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .limit(size)
                .boxed()
                .collect(Collectors.toList());
    }

    List<Integer> createDummyResult(Pageable pageable) {
        return createDummyResult(pageable.getPageSize());
    }

    Integer getDummyKeyFunction(Integer data) {
        return data;
    }
}
