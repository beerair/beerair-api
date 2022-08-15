package com.beerair.core.member.infrastructure;

import com.beerair.core.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    @Transactional(readOnly = true)
    Optional<Member> findByEmail(String email);
}
