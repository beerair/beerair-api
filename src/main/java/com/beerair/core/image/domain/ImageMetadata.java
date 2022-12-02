package com.beerair.core.image.domain;

import com.beerair.core.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "image_metadata")
public class ImageMetadata extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "member_id")
    private String memberId;

    public ImageMetadata(String filename, String imageUrl, String memberId) {
        this.filename = filename;
        this.imageUrl = imageUrl;
        this.memberId = memberId;
    }
}
