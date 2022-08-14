package com.beerair.core.suggest.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.suggest.domain.vo.SuggestStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeerSuggest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String beerName;

    private String imageUrls;

    private LocalDateTime completedAt;

    private String rejectReason;

    private SuggestStatus status;

    private Long memberId;

    public BeerSuggest(String name, String urls, Long memberId) {
        this.beerName = name;
        this.imageUrls = urls;
        this.status = SuggestStatus.PROCEED;
        this.memberId = memberId;
    }
}
