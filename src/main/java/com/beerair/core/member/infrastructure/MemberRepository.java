package com.beerair.core.member.infrastructure;

import com.beerair.core.member.domain.Member;
import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.dto.query.MemberDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query("SELECT m as member, l as level " +
            "FROM Member m " +
            "INNER JOIN Level l ON l.id = m.levelId " +
            "WHERE m.id = :id " +
            "AND m.deletedAt IS NULL")
    Optional<MemberDto> findByIdWithLevel(String id);

    boolean existsByNickname(String nickname);
}
