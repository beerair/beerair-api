package com.beerair.core.image.application;

import com.beerair.core.client.s3.S3Client;
import com.beerair.core.error.exception.image.ImageException;
import com.beerair.core.image.domain.ImageMetadata;
import com.beerair.core.image.dto.response.ImageResponse;
import com.beerair.core.image.infrastructure.ImageMetadataRepository;
import com.beerair.core.member.dto.LoggedInMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMetadataRepository imageMetadataRepository;
    private final S3Client s3Client;

    @Transactional
    public ImageResponse uploadImage(LoggedInMember member, MultipartFile image) {
        try {
            s3Client.upload(image);
        } catch (Exception e) {
            log.error("image error", e);
            throw new ImageException();
        }

        var filename = image.getOriginalFilename();

        var imageMetadata = imageMetadataRepository.save(
                new ImageMetadata(filename, s3Client.imageUrl(filename), member.getId())
        );

        return new ImageResponse(imageMetadata);
    }
}
