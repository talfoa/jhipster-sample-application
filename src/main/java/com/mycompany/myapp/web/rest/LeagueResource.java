package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.LeagueService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.LeagueDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.League}.
 */
@RestController
@RequestMapping("/api")
public class LeagueResource {

    private final Logger log = LoggerFactory.getLogger(LeagueResource.class);

    private static final String ENTITY_NAME = "league";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeagueService leagueService;

    public LeagueResource(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    /**
     * {@code POST  /leagues} : Create a new league.
     *
     * @param leagueDTO the leagueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leagueDTO, or with status {@code 400 (Bad Request)} if the league has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leagues")
    public ResponseEntity<LeagueDTO> createLeague(@Valid @RequestBody LeagueDTO leagueDTO) throws URISyntaxException {
        log.debug("REST request to save League : {}", leagueDTO);
        if (leagueDTO.getId() != null) {
            throw new BadRequestAlertException("A new league cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeagueDTO result = leagueService.save(leagueDTO);
        return ResponseEntity.created(new URI("/api/leagues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leagues} : Updates an existing league.
     *
     * @param leagueDTO the leagueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leagueDTO,
     * or with status {@code 400 (Bad Request)} if the leagueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leagueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leagues")
    public ResponseEntity<LeagueDTO> updateLeague(@Valid @RequestBody LeagueDTO leagueDTO) throws URISyntaxException {
        log.debug("REST request to update League : {}", leagueDTO);
        if (leagueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LeagueDTO result = leagueService.save(leagueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leagueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /leagues} : get all the leagues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leagues in body.
     */
    @GetMapping("/leagues")
    public List<LeagueDTO> getAllLeagues() {
        log.debug("REST request to get all Leagues");
        return leagueService.findAll();
    }

    /**
     * {@code GET  /leagues/:id} : get the "id" league.
     *
     * @param id the id of the leagueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leagueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leagues/{id}")
    public ResponseEntity<LeagueDTO> getLeague(@PathVariable Long id) {
        log.debug("REST request to get League : {}", id);
        Optional<LeagueDTO> leagueDTO = leagueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leagueDTO);
    }

    /**
     * {@code DELETE  /leagues/:id} : delete the "id" league.
     *
     * @param id the id of the leagueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leagues/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable Long id) {
        log.debug("REST request to delete League : {}", id);
        leagueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
