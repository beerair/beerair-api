package com.beerair.core.review.domain;

import com.beerair.core.common.LongArrayToStringConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.AttributeOverride;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class FlavorIds {

    @Comment("맥주 맛 Id (최대 3개)")
    @Convert(converter = LongArrayToStringConverter.class)
    private List<Long> values;

    public static FlavorIds from(List<Long> values) {
        return new FlavorIds(values);
    }
}
