package com.beerair.core.review.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FlavorsResponse {
    private final List<FlavorResponse> flavors;
}
