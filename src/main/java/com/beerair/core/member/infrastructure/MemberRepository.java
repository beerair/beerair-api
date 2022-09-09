package com.beerair.core.member.infrastructure;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.dto.response.MemberResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("SELECT m " +
            "FROM Member m " +
            "WHERE m.social = :social " +
            "AND m.deletedAt IS NULL")
    Optional<Member> findBySocial(@Param("social") MemberSocial social);

    @Query("SELECT m " +
            "FROM Member m " +
            "WHERE m.id = :id " +
            "AND m.deletedAt IS NULL")
    Optional<Member> findById(String id);

    @Query("SELECT new com.beerair.core.member.dto.response.MemberResponse(m, l) " +
            "FROM Member m " +
            "INNER JOIN Level l ON l.id = m.levelId " +
            "WHERE m.id = :id " +
            "AND m.deletedAt IS NULL")
    Optional<MemberResponse> findByIdWithLevel(String id);

    boolean existsByNickname(String nickname);
}
