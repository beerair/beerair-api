package com.beerair.core.suggest.domain;

import com.beerair.core.beer.domain.vo.SuggestStatus;
import com.beerair.core.common.domain.BaseEntity;
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

    private String imageUrl;

    private String beerName;

    private LocalDateTime completedAt;

    private String rejectReason;

    private SuggestStatus status;

    private Long memberId;
}
