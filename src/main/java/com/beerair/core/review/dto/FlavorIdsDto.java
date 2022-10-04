package com.beerair.core.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class FlavorIdsDto {

    @JsonProperty
    private final List<Long> values;
}
