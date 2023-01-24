package com.beerair.core.suggest.dto.response;

import com.beerair.core.common.util.MapperUtil;
import com.beerair.core.suggest.domain.Suggest;
import com.beerair.core.suggest.domain.vo.SuggestStatus;
import lombok.Data;

import java.util.List;

@Data
public class SuggestRegisterResponse {
    private final Long id;
    private final String beerName;
    private final List<String> images;
    private final SuggestStatus status;

    @SuppressWarnings("UNCHECKED_CAST")
    public static SuggestRegisterResponse from(Suggest suggest) {
        var images = (List<String>) MapperUtil.readValue(suggest.getImageUrls(), List.class);

        return new SuggestRegisterResponse(
                suggest.getId(),
                suggest.getBeerName(),
                images,
                suggest.getStatus()
        );
    }
}
