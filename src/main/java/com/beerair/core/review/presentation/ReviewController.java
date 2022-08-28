package com.beerair.core.review.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import com.beerair.core.review.application.FlavorService;
import com.beerair.core.review.application.ReviewService;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.facade.ReviewCreateFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[7] 리뷰 API")
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewCreateFacade reviewFacade;
    private final FlavorService flavorService;

    @ApiOperation(value = "특정 맥주의 맛 top3 API")
    @GetMapping("flavors-top3")
    public ResponseEntity<?> getFlavorTop3(
            @RequestParam("beerId") String beerId
    ) {
        var response = flavorService.getFlavorTop3(beerId);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "리뷰 티켓 조회")
    @GetMapping
    public ResponseEntity<?> get(
            @AuthMember LoggedInMember member,
            @RequestParam("beerId") String beerId
    ) {
        var response = reviewService.get(member.getId(), beerId);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "리뷰 티켓 등록")
    @PostMapping
    public ResponseEntity<?> create(@AuthMember LoggedInMember member, @RequestBody ReviewRequest request) {
        reviewFacade.create(member.getId(), request);
        return ResponseDto.noContent();
    }
}
