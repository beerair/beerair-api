package com.beerair.core.auth.infrastructure;

import com.beerair.core.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    @Query("SELECT rt FROM RefreshToken rt WHERE rt.token = :token AND rt.deletedAt IS NULL")
    Optional<RefreshToken> findByToken(@Param("token") String token);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.memberId = :memberId AND rt.deletedAt IS NULL")
    List<RefreshToken> findAllByMemberId(@Param("memberId") String memberId);
}
