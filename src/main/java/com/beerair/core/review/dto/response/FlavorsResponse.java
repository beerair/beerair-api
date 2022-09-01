package com.beerair.core.review.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FlavorsResponse {
    private final List<FlavorResponse> flavors;
}
