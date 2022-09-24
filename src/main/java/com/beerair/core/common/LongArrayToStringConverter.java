package com.beerair.core.common;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LongArrayToStringConverter implements AttributeConverter<List<Long>, String> {

    private static final String COMMA_SPLITTER = ",";

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        return attribute.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(COMMA_SPLITTER));
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(COMMA_SPLITTER))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
