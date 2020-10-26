package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.GameEvent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GameEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, Long> {
}
