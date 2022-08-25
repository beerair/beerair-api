package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.dto.query.BeerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {
	@Transactional(readOnly = true)
	@Query(value = "SELECT b as beer, " +
	               "c as country, bt as beerType, false as liked " +
	               "FROM Beer b " +
	               "JOIN BeerType bt on b.typeId = bt.id " +
	               "JOIN Country c on b.countryId = c.id " +
	               "WHERE b.id = :beerId and b.deletedAt is null")
	Optional<BeerDto> findByIdWithTypeAndCountry(@Param("beerId") String beerId);

	@Transactional(readOnly = true)
	@Query(value = "SELECT b as beer, " +
	               "c as country, bt as beerType, (bl is not null) as liked, r as myReview " +
	               "FROM Beer b " +
	               "JOIN BeerType bt on b.typeId = bt.id " +
	               "JOIN Country c on b.countryId = c.id " +
	               "LEFT OUTER JOIN BeerLike bl on b.id = bl.beerId and bl.memberId = :memberId " +
				   "LEFT OUTER JOIN Review r ON r.beerId = b.id AND r.memberId = :memberId AND r.deletedAt IS NULL " +
	               "WHERE b.id = :beerId and b.deletedAt is null")
	Optional<BeerDto> findByIdWithTypeAndCountry(@Param("beerId") String beerId, @Param("memberId") String memberId);

    @Transactional(readOnly = true)
    Boolean findByKorNameOrEngName(String korName, String engName);
}
