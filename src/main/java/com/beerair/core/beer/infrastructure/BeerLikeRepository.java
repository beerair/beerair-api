package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.BeerLike;
import com.beerair.core.beer.dto.query.BeerListItemDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BeerLikeRepository extends JpaRepository<BeerLike, Long> {
    @Modifying
    @Query("DELETE FROM BeerLike bl WHERE bl.memberId = :memberId AND bl.beerId = :beerId")
    Integer deleteByBeerIdAndMemberId(@Param("memberId") String memberId, @Param("beerId") String beerId);

    @Transactional(readOnly = true)
    @Query("SELECT COUNT(bl) FROM BeerLike bl WHERE bl.memberId = :memberId")
    Integer findCountByMemberId(@Param("memberId") String memberId);

    @Transactional(readOnly = true)
    @Query("SELECT new com.beerair.core.beer.dto.query.BeerListItemDto(" +
            "b.id, b.alcohol, b.korName, b.imageUrl, " +
            "c.korName, " +
            "bt.korName," +
            "r.feelStatus, " +
            "true) " +
            "FROM Beer b " +
            "INNER JOIN BeerLike bl ON b.id = bl.beerId AND bl.memberId = :memberId " +
            "INNER JOIN Country c ON b.countryId = c.id " +
            "INNER JOIN BeerType bt ON b.typeId = bt.id " +
            "LEFT OUTER JOIN Review r ON r.beerId = b.id AND r.memberId = :memberId AND r.deletedAt IS NULL " +
            "WHERE b.deletedAt IS NULL")
    List<BeerListItemDto> findAllByMemberIdWithBeer(@Param("memberId") String memberId);
}
