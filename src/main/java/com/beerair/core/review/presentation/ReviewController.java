package com.beerair.core.review.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import com.beerair.core.review.application.ReviewCommandService;
import com.beerair.core.review.application.ReviewQueryService;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.facade.ReviewCreateFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[7] 리뷰 API")
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService queryService;
    private final ReviewCommandService commandService;
    private final ReviewCreateFacade createFacade;

    @ApiOperation(value = "나의 리뷰 티켓 조회")
    @GetMapping("{id}")
    public ResponseEntity<?> get(
            @AuthMember LoggedInMember member,
            @PathVariable("id") String reviewId
    ) {
        var response = queryService.get(member.getId(), reviewId);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "리뷰 티켓 등록")
    @PostMapping
    public ResponseEntity<?> create(@AuthMember LoggedInMember member, @RequestBody ReviewRequest request) {
        createFacade.create(member.getId(), request);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "나의 리뷰 티켓 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
            @AuthMember LoggedInMember member,
            @PathVariable("id") String reviewId
    ) {
        commandService.delete(member.getId(), reviewId);
        return ResponseDto.noContent();
    }
}
