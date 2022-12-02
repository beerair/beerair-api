package com.beerair.core.image.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.image.application.ImageService;
import com.beerair.core.image.dto.response.ImageResponse;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[11] 이미지 API")
@RestController
@RequestMapping(value = "/api/v1/images", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "이미지 업로드 api")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<ImageResponse> upload(
            @AuthMember LoggedInMember member,
            @RequestPart(name = "image", required = true) MultipartFile image) {
        var response = imageService.uploadImage(member, image);
        return new ResponseDto<>(response);
    }
}
