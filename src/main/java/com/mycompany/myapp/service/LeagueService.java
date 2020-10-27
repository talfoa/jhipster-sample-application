package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LeagueDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.League}.
 */
public interface LeagueService {

    /**
     * Save a league.
     *
     * @param leagueDTO the entity to save.
     * @return the persisted entity.
     */
    LeagueDTO save(LeagueDTO leagueDTO);

    /**
     * Get all the leagues.
     *
     * @return the list of entities.
     */
    List<LeagueDTO> findAll();


    /**
     * Get the "id" league.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LeagueDTO> findOne(Long id);

    /**
     * Delete the "id" league.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
