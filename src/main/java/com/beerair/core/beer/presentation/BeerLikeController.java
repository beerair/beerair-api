package com.beerair.core.beer.presentation;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

import com.beerair.core.beer.application.BeerLikeService;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "[9] 맥주 찜(좋아요) API")
@RestController
@RequestMapping(value = "/api/v1/beer-likes", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerLikeController {
    private final BeerLikeService beerLikeService;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "맥주 찜(좋아요) 목록 조회 api")
    @GetMapping
    public ResponseDto<List<BeerResponse>> getAll(@AuthMember LoggedInMember member) {
        var result = beerLikeService.getAll(member.getId());
        return new ResponseDto<>(result);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "맥주 찜하기(좋아요) api")
    @PostMapping
    public ResponseDto<Void> like(
            @AuthMember LoggedInMember member,
            @RequestParam("beerId") Integer beerId
    ) {
        beerLikeService.like(member.getId(), beerId);
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "맥주 찜하기(좋아요) 해제 api")
    @DeleteMapping
    public ResponseDto<Void> unlike(
            @AuthMember LoggedInMember member,
            @RequestParam("beerId") Integer beerId
    ) {
        beerLikeService.unlike(member.getId(), beerId);
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "찜한(좋아요) 맥주 count api")
    @GetMapping("/count")
    public ResponseDto<Integer> getCount(@AuthMember LoggedInMember member) {
        var result = beerLikeService.getCount(member.getId());
        return new ResponseDto<>(result);
    }

}
