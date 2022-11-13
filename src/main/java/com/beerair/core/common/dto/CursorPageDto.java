package com.beerair.core.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class CursorPageDto<KEY, T> implements Serializable {
    private final List<T> data;
    private final KEY nextCursor;
    private final Integer size;
    private final Boolean hasNext;

    public CursorPageDto(List<T> data, KEY nextCursor) {
        this.nextCursor = nextCursor;
        this.hasNext = Objects.nonNull(nextCursor);
        this.data = data;
        this.size = data.size();
    }
}
