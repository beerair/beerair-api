package com.beerair.core.common.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NativeQueryReader {
    private final Object[] row;

    public NativeQueryReader(Object row) {
        this((Object[]) row);
    }

    public String getString(int index) {
        return (String) row[index];
    }

    public Float getFloat(int index) {
        return (Float) row[index];
    }

    public Long getLong(int index) {
        var value = row[index];
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        throw new ClassCastException();
    }

    public Boolean getBoolean(int index) {
        var value = row[index];
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue() > 0;
        }
        throw new ClassCastException();
    }

    public int size() {
        return row.length;
    }
}
