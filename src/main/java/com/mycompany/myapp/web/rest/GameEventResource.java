package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.GameEventService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.GameEventDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.GameEvent}.
 */
@RestController
@RequestMapping("/api")
public class GameEventResource {

    private final Logger log = LoggerFactory.getLogger(GameEventResource.class);

    private static final String ENTITY_NAME = "gameEvent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GameEventService gameEventService;

    public GameEventResource(GameEventService gameEventService) {
        this.gameEventService = gameEventService;
    }

    /**
     * {@code POST  /game-events} : Create a new gameEvent.
     *
     * @param gameEventDTO the gameEventDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gameEventDTO, or with status {@code 400 (Bad Request)} if the gameEvent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/game-events")
    public ResponseEntity<GameEventDTO> createGameEvent(@RequestBody GameEventDTO gameEventDTO) throws URISyntaxException {
        log.debug("REST request to save GameEvent : {}", gameEventDTO);
        if (gameEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new gameEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GameEventDTO result = gameEventService.save(gameEventDTO);
        return ResponseEntity.created(new URI("/api/game-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /game-events} : Updates an existing gameEvent.
     *
     * @param gameEventDTO the gameEventDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gameEventDTO,
     * or with status {@code 400 (Bad Request)} if the gameEventDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gameEventDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/game-events")
    public ResponseEntity<GameEventDTO> updateGameEvent(@RequestBody GameEventDTO gameEventDTO) throws URISyntaxException {
        log.debug("REST request to update GameEvent : {}", gameEventDTO);
        if (gameEventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GameEventDTO result = gameEventService.save(gameEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gameEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /game-events} : get all the gameEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gameEvents in body.
     */
    @GetMapping("/game-events")
    public List<GameEventDTO> getAllGameEvents() {
        log.debug("REST request to get all GameEvents");
        return gameEventService.findAll();
    }

    /**
     * {@code GET  /game-events/:id} : get the "id" gameEvent.
     *
     * @param id the id of the gameEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gameEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/game-events/{id}")
    public ResponseEntity<GameEventDTO> getGameEvent(@PathVariable Long id) {
        log.debug("REST request to get GameEvent : {}", id);
        Optional<GameEventDTO> gameEventDTO = gameEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gameEventDTO);
    }

    /**
     * {@code DELETE  /game-events/:id} : delete the "id" gameEvent.
     *
     * @param id the id of the gameEventDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/game-events/{id}")
    public ResponseEntity<Void> deleteGameEvent(@PathVariable Long id) {
        log.debug("REST request to delete GameEvent : {}", id);
        gameEventService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
