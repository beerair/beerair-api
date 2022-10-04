package com.beerair.core.review.dto.request;

import com.beerair.core.review.domain.vo.FeelStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String content;
    private FeelStatus feelStatus;
    private String imageUrl;
    private Boolean isPublic;
    private List<Long> flavorIds;
    private String beerId;
}
