package com.beerair.core.suggest.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.suggest.domain.vo.SuggestStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String beerName;

    private String imageUrls;

    private LocalDateTime completedAt;

    private String rejectReason;

    private SuggestStatus status;

    private String memberId;

    public Suggest(String name, String urls, String memberId) {
        this.beerName = name;
        this.imageUrls = urls;
        this.status = SuggestStatus.PROCEED;
        this.memberId = memberId;
    }
}
