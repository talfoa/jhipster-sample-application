package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.SportDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Sport}.
 */
public interface SportService {

    /**
     * Save a sport.
     *
     * @param sportDTO the entity to save.
     * @return the persisted entity.
     */
    SportDTO save(SportDTO sportDTO);

    /**
     * Get all the sports.
     *
     * @return the list of entities.
     */
    List<SportDTO> findAll();


    /**
     * Get the "id" sport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SportDTO> findOne(Long id);

    /**
     * Delete the "id" sport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
