package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ScoreBoardDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ScoreBoard}.
 */
public interface ScoreBoardService {

    /**
     * Save a scoreBoard.
     *
     * @param scoreBoardDTO the entity to save.
     * @return the persisted entity.
     */
    ScoreBoardDTO save(ScoreBoardDTO scoreBoardDTO);

    /**
     * Get all the scoreBoards.
     *
     * @return the list of entities.
     */
    List<ScoreBoardDTO> findAll();


    /**
     * Get the "id" scoreBoard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ScoreBoardDTO> findOne(Long id);

    /**
     * Delete the "id" scoreBoard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
