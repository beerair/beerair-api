package com.beerair.core.member.domain;

import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Levels {
    private final List<Level> values;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Level getByExp(int exp) {
        return values.stream()
            .sorted(Comparator.comparingInt(Level::getTier).reversed())
            .filter(each -> exp >= each.getExp())
            .findFirst()
            .get();
    }
}
