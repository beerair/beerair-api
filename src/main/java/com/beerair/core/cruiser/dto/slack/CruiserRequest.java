package com.beerair.core.cruiser.dto.slack;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CruiserRequest {
    private final String text;
}
