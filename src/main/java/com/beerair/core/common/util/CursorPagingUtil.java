package com.beerair.core.common.util;

import com.beerair.core.common.dto.CursorPageDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public final class CursorPagingUtil {
    public static <KEY, T> CursorPageDto<KEY, T> paging(Function<Pageable, List<T>> query, Function<T, KEY> getKeyFunction, int limit) {
        return null;
    }
}
