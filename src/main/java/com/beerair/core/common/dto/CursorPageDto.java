package com.beerair.core.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Data
public class CursorPageDto<KEY, T> implements Serializable {
    private List<T> data;
    private KEY nextCursor;
    private Integer size;
    private Boolean hasNext;

    protected CursorPageDto() {
    }

    public CursorPageDto(List<T> data, KEY nextCursor) {
        this.nextCursor = nextCursor;
        this.hasNext = Objects.nonNull(nextCursor);
        this.data = data;
        this.size = data.size();
    }

    public CursorPageDto(List<T> data) {
        this(data, null);
    }
}
