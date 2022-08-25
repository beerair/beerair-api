package com.beerair.core.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FlavorIdsDto {

    @JsonProperty
    private final List<Long> values;
}
