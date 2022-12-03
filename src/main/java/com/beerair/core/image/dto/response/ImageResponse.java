package com.beerair.core.image.dto.response;

import com.beerair.core.image.domain.ImageMetadata;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageResponse {
    private final Long id;
    private final String filename;
    private final String imageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    public ImageResponse(ImageMetadata imageMetadata) {
        this.id = imageMetadata.getId();
        this.filename = imageMetadata.getFilename();
        this.imageUrl = imageMetadata.getImageUrl();
        this.createdAt = imageMetadata.getCreatedAt();
    }
}
