package com.beerair.core.beer.infrastructure.search;

import com.beerair.core.beer.dto.query.BeerListItemDto;
import com.beerair.core.beer.infrastructure.BeerSearchRepository;
import com.beerair.core.common.util.NativeQueryReader;
import com.beerair.core.review.domain.vo.FeelStatus;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Repository
public class BeerSearchRepositoryImpl implements BeerSearchRepository {
    private static final String CHAR_SET = "UTF-8";
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<BeerListItemDto> search(String memberId, BeerSearchCondition condition, BeerOrderBy order, int offset, int limit) {
        List<?> result = createQuery(memberId, condition, order, offset, limit).getResultList();
        return result.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Query createQuery(String memberId, BeerSearchCondition condition, BeerOrderBy order, int offset, int limit) {
        List<Consumer<Query>> queryVisitors = new ArrayList<>();
        String sql = String.join(" ", List.of(
                select(memberId),
                from(queryVisitors, memberId, order),
                where(queryVisitors, condition),
                orderBy(order)
        ));

        var query = em.createNativeQuery(sql);
        queryVisitors.forEach(each -> each.accept(query));
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query;
    }

    private String select(String memberId) {
        boolean isMember = Objects.nonNull(memberId);

        var sql = new ArrayList<>(List.of(
                "SELECT b.id as id, b.alcohol as alcohol, b.kor_name as korName, b.image_url as imageUrl",
                "c.kor_name as country",
                "bt.kor_name as type"
        ));
        if (isMember) {
            sql.add("r.feel_status as myFeelStatus");
            sql.add("bl.id IS NOT NULL as liked");
        }
        return String.join(",", sql);
    }

    private String from(List<Consumer<Query>> queryVisitors, String memberId, BeerOrderBy order) {
        boolean isMember = Objects.nonNull(memberId);
        boolean orderIsReview = order == BeerOrderBy.REVIEW;

        var sql = new ArrayList<>(List.of(
                "FROM beer b",
                "INNER JOIN beer_type bt on b.type_id = bt.id",
                "INNER JOIN country c on b.country_id = c.id"
        ));
        if (isMember) {
            sql.add("LEFT OUTER JOIN review r ON r.beer_id = b.id AND r.member_id = :memberId AND r.deleted_at IS NULL");
            sql.add("LEFT OUTER JOIN beer_like bl on b.id = bl.beer_id and bl.member_id = :memberId");
            queryVisitors.add(query ->
                    query.setParameter("memberId", memberId)
            );
        }
        if (orderIsReview) {
            sql.add("LEFT JOIN (SELECT beer_id, COUNT(*) as cnt FROM review r WHERE deleted_at IS NULL GROUP BY beer_id) rc ON rc.beer_id = b.id");
        }
        return String.join(" ", sql);
    }

    @SneakyThrows
    private String where(List<Consumer<Query>> queryVisitors, BeerSearchCondition condition) {
        var sql = new ArrayList<String>();
        sql.add("WHERE b.deleted_at IS NULL");
        if (Objects.nonNull(condition.getKeyword())) {
            sql.add("AND b.kor_name LIKE :keyword");

            String keyword = URLDecoder.decode(condition.getKeyword(), CHAR_SET);
            queryVisitors.add(query ->
                    query.setParameter("keyword", "%" + keyword + "%")
            );
        }
        var countries = condition.getCountries();
        if (Objects.nonNull(countries)) {
            sql.add("AND c.id IN (:countries)");
            queryVisitors.add(query ->
                    query.setParameter("countries", condition.getCountries())
            );
        }
        var types = condition.getTypes();
        if (Objects.nonNull(types)) {
            sql.add("AND bt.id IN (:types)");
            queryVisitors.add(query ->
                    query.setParameter("types", condition.getTypes())
            );
        }
        return String.join(" ", sql);
    }

    private String orderBy(BeerOrderBy order) {
        if (order == BeerOrderBy.REVIEW) {
            return "ORDER BY rc.cnt DESC";
        }
        if (order == BeerOrderBy.NAME) {
            return "ORDER BY c.kor_name";
        }
        if (order == BeerOrderBy.ALCOHOL_HIGHEST) {
            return "ORDER BY b.alcohol DESC";
        }
        if (order == BeerOrderBy.ALCOHOL_LOWEST) {
            return "ORDER BY b.alcohol";
        }
        return "";
    }

    private BeerListItemDto convert(Object row) {
        var reader = new NativeQueryReader(row);

        var builder = BeerListItemDto.builder()
                .id(reader.getString(0))
                .alcohol(reader.getFloat(1))
                .korName(reader.getString(2))
                .imageUrl(reader.getString(3))
                .country(reader.getString(4))
                .type(reader.getString(5));

        var isMember = reader.size() > 6;
        if (isMember) {
            var feel = reader.getString(6);
            builder.myFeelStatus(Objects.nonNull(feel) ? FeelStatus.valueOf(feel) : null)
                    .liked(reader.getBoolean(7));
        }
        return builder.build();
    }
}
