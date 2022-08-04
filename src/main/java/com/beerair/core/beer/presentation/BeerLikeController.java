package com.beerair.core.beer.presentation;

import com.beerair.core.beer.application.BeerLikeService;
import com.beerair.core.common.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[9] 맥주 찜(좋아요) API")
@RestController
@RequestMapping(value = "/api/v1/beer-likes", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerLikeController {
    private final BeerLikeService beerLikeService;

    @ApiOperation(value = "맥주 찜(좋아요) 목록 조회 api")
    @GetMapping
    public ResponseEntity<Void> getAll() {
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "맥주 찜하기(좋아요) api")
    @PostMapping
    public ResponseEntity<Void> save(
            @RequestParam("beerId") Long beerId
    ) {
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "찜한(좋아요) 맥주 count api")
    @GetMapping("/count")
    public ResponseEntity<Void> count() {
        return ResponseDto.noContent();
    }

}