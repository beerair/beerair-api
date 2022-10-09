package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.dto.query.BeerListItemDto;
import com.beerair.core.common.util.NativeQueryReader;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BeerRecommendRepositoryImpl implements BeerRecommendRepository {
    private static final String SQL =
            "SELECT " +
            "b.id, b.alcohol, b.kor_name, b.image_url, " +
            "c.kor_name as country, " +
            "bt.kor_name as type," +
            "bl.id IS NOT NULL as liked " +
            "FROM beer b " +
            "INNER JOIN country c ON b.country_id = c.id " +
            "INNER JOIN beer_type bt ON b.type_id = bt.id " +
            "LEFT OUTER JOIN beer_like bl ON b.id = bl.beer_id AND bl.member_id = :memberId " +
            "LEFT OUTER JOIN review r ON r.beer_id = b.id AND r.member_id = :memberId AND r.deleted_at IS NULL " +
            "WHERE b.deleted_at IS NULL AND r.id IS NULL " +
            "ORDER BY RAND()";
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BeerListItemDto> findRecommends(String memberId, int limit) {
        List<?> result = createQuery(memberId, limit).getResultList();
        return result.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Query createQuery(String memberId, int limit) {
        var query = em.createNativeQuery(SQL);
        query.setParameter("memberId", memberId);
        query.setMaxResults(limit);
        return query;
    }

    private BeerListItemDto convert(Object row) {
        var reader = new NativeQueryReader(row);
        return BeerListItemDto.builder()
                .id(reader.getString(0))
                .alcohol(reader.getFloat(1))
                .korName(reader.getString(2))
                .imageUrl(reader.getString(3))
                .country(reader.getString(4))
                .type(reader.getString(5))
                .liked(reader.getBoolean(6))
                .build();
    }
}
