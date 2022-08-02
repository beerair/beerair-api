package com.beerair.core.review.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.review.application.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[7] 리뷰 API")
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @ApiOperation(value = "특정 맥주의 맛 top3 API", notes = "MOCK UP API")
    @GetMapping("/beers/{beerId}/flavors-top3/")
    public ResponseEntity<?> getFlavorTop3(
            @PathVariable("beerId") Long beerId
    ) {
        return ResponseDto.ok("ok");
    }

    @ApiOperation(value = "리뷰 티켓 조회", notes = "MOCK UP API")
    @GetMapping("/beers/{beerId}")
    public ResponseEntity<?> get(
            @PathVariable("beerId") Long beerId
    ) {
        return ResponseDto.ok("ok");
    }
}
