package com.beerair.core.client.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.beerair.core.common.util.EncoderUtil;
import com.beerair.core.config.s3.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Client {
    private final S3Properties s3Properties;
    private final AmazonS3 amazonS3;

    public void upload(MultipartFile file) throws IOException {
        var request = new PutObjectRequest(
                imageBucketUrl(),
                file.getOriginalFilename(),
                file.getInputStream(),
                objectMetadata(file)
        ).withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(request);
    }

    private ObjectMetadata objectMetadata(MultipartFile file) throws IOException {
        var metadata = new ObjectMetadata();

        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getBytes().length);

        return metadata;
    }

    public String imageBucketUrl() {
        return s3Properties.getBucket() + "/images";
    }

    public String imageUrl(String filename) {
        return s3Properties.getBucketUrl() + "/images/" + EncoderUtil.encodeUrl(filename);
    }
}
