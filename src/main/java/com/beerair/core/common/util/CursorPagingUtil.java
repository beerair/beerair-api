package com.beerair.core.common.util;

import com.beerair.core.common.dto.CursorPageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public final class CursorPagingUtil {
    public static <KEY, T> CursorPageDto<KEY, T> paging(Function<Pageable, List<T>> query, Function<T, KEY> getKeyFunction, int limit) {
        var pageable = PageRequest.ofSize(limit + 1);
        var result = query.apply(pageable);

        if (result.size() <= limit) {
            return new CursorPageDto<>(result);
        }
        KEY nextCursor = popNextCursor(result, getKeyFunction);
        return new CursorPageDto<>(result, nextCursor);
    }

    private static <T, KEY> KEY popNextCursor(List<T> data, Function<T, KEY> getKeyFunction) {
        T nextData = data.get(data.size() - 1);
        data.remove(nextData);
        return getKeyFunction.apply(nextData);
    }
}
