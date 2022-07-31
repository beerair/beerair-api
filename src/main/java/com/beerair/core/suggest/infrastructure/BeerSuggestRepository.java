package com.beerair.core.suggest.infrastructure;

import com.beerair.core.suggest.domain.BeerSuggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerSuggestRepository extends JpaRepository<BeerSuggest, Long> {
}
