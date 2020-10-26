package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ScoreBoard;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ScoreBoard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScoreBoardRepository extends JpaRepository<ScoreBoard, Long> {
}
