package com.beerair.core.suggest.infrastructure;

import com.beerair.core.suggest.domain.BeerSuggest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BeerSuggestRepository extends JpaRepository<BeerSuggest, Long> {
    @Transactional(readOnly = true)
    Boolean existsByBeerNameAndMemberId(String name, String memberId);

    @Transactional(readOnly = true)
    Long countByMemberId(String memberId);

    @Transactional(readOnly = true)
    Page<BeerSuggest> findAllByMemberId(Pageable pageable, String memberId);
}
