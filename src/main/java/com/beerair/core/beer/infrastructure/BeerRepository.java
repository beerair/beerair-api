package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.dto.response.BeerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {
	@Transactional(readOnly = true)
	@Query(value = "SELECT b as beer, " +
	               "c as country, bt as beerType, false as liked " +
	               "FROM Beer b " +
	               "JOIN BeerType bt on b.typeId = bt.id " +
	               "JOIN Country c on b.countryId = c.id " +
	               "LEFT OUTER JOIN BeerLike bl on b.id = bl.beerId " +
	               "WHERE b.id = :beerId and b.deletedAt is null")
	Optional<BeerDto> findByIdWithTypeAndCountry(@Param("beerId") Long beerId);

	@Transactional(readOnly = true)
	@Query(value = "SELECT b as beer, " +
	               "c as country, bt as beerType, (bl is not null) as liked " +
	               "FROM Beer b " +
	               "JOIN BeerType bt on b.typeId = bt.id " +
	               "JOIN Country c on b.countryId = c.id " +
	               "LEFT OUTER JOIN BeerLike bl on b.id = bl.beerId and bl.memberId = :memberId " +
	               "WHERE b.id = :beerId and b.deletedAt is null")
	Optional<BeerDto> findByIdWithTypeAndCountry(@Param("beerId") Long beerId, @Param("memberId") Long memberId);

    @Transactional(readOnly = true)
    Boolean findByKorNameOrEngName(String korName, String engName);
}
