package com.beerair.core.event;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ReviewCreateEventArgs {
    private final String reviewId;
    private final String beerId;
    private final String memberId;
}