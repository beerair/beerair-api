package com.beerair.core.review.presentation;

import com.beerair.core.review.application.ReviewService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[7] 리뷰 API")
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
}
