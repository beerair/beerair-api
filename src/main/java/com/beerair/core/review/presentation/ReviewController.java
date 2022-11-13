package com.beerair.core.review.presentation;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

import com.beerair.core.common.dto.CursorPageDto;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import com.beerair.core.review.application.ReviewCommandService;
import com.beerair.core.review.application.ReviewQueryService;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.beerair.core.review.facade.ReviewCreateFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[7] 리뷰 API")
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService queryService;
    private final ReviewCommandService commandService;
    private final ReviewCreateFacade createFacade;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "나의 리뷰 티켓 조회")
    @GetMapping("{id}")
    public ResponseDto<ReviewResponse> get(
            @AuthMember LoggedInMember member,
            @PathVariable("id") Integer reviewId
    ) {
        var result = queryService.get(member.getId(), reviewId);
        return new ResponseDto<>(result);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "리뷰 티켓 등록")
    @PostMapping
    public ResponseDto<Void> create(@AuthMember LoggedInMember member, @RequestBody ReviewRequest request) {
        createFacade.create(member.getId(), request);
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "나의 리뷰 티켓 삭제")
    @DeleteMapping("{id}")
    public ResponseDto<Void> delete(
            @AuthMember LoggedInMember member,
            @PathVariable("id") Integer reviewId
    ) {
        commandService.delete(member.getId(), reviewId);
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "나의 리뷰 티켓 목록 조회")
    @GetMapping("me")
    public ResponseDto<List<ReviewResponse>> getAllByMe(
            @AuthMember LoggedInMember member,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        limit = Objects.requireNonNullElse(limit, Integer.MAX_VALUE);
        var result = queryService.getAllByMe(member.getId(), limit);
        return new ResponseDto<>(result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "특정 맥주에 대한 리뷰 조회")
    @GetMapping
    public CursorPageDto<Integer, ReviewResponse> getAllByBeer(
            @RequestParam(value = "beerId") Integer beerId,
            @RequestParam(value = "cursor", required = false) Integer cursor,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit
    ) {
        return queryService.getAllByBeer(beerId, cursor, limit);
    }
}
