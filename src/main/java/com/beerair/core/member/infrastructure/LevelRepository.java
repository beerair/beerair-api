package com.beerair.core.member.infrastructure;

import com.beerair.core.member.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, String> {
}
