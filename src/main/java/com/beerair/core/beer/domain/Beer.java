package com.beerair.core.beer.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.KeyGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static com.beerair.core.common.util.KeyGenerator.UUID_LENGTH;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Beer extends BaseEntity {
    @Id
    @Column(length = UUID_LENGTH)
    private String id;

    private Float alcohol;

    private String content;

    private String imageUrl;

    private String korName;

    private String engName;

    private Long typeId;

    private Integer volume;

    private Integer price;

    private Long countryId;

    @Builder
    private Beer(
            Float alcohol,
            String content,
            String imageUrl,
            String korName,
            String engName,
            Long typeId,
            Integer volume,
            Integer price,
            Long countryId
    ) {
        this.id = KeyGenerator.createKeyByUUID();
        this.alcohol = alcohol;
        this.content = content;
        this.imageUrl = imageUrl;
        this.korName = korName;
        this.engName = engName;
        this.typeId = typeId;
        this.volume = volume;
        this.price = price;
        this.countryId = countryId;
    }
}
