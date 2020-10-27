package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.GameEventDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.GameEvent}.
 */
public interface GameEventService {

    /**
     * Save a gameEvent.
     *
     * @param gameEventDTO the entity to save.
     * @return the persisted entity.
     */
    GameEventDTO save(GameEventDTO gameEventDTO);

    /**
     * Get all the gameEvents.
     *
     * @return the list of entities.
     */
    List<GameEventDTO> findAll();


    /**
     * Get the "id" gameEvent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GameEventDTO> findOne(Long id);

    /**
     * Delete the "id" gameEvent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
