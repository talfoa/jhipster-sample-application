package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.GameEvent;
import com.mycompany.myapp.repository.GameEventRepository;
import com.mycompany.myapp.service.GameEventService;
import com.mycompany.myapp.service.dto.GameEventDTO;
import com.mycompany.myapp.service.mapper.GameEventMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.GameEventType;
import com.mycompany.myapp.domain.enumeration.TeamHomeOrAway;
/**
 * Integration tests for the {@link GameEventResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GameEventResourceIT {

    private static final Instant DEFAULT_TIME_OF_MESSAGE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_OF_MESSAGE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_GAME_EVENT_ID = 1L;
    private static final Long UPDATED_GAME_EVENT_ID = 2L;

    private static final GameEventType DEFAULT_EVENT_TYPE = GameEventType.GOAL;
    private static final GameEventType UPDATED_EVENT_TYPE = GameEventType.YELLOW_CARD;

    private static final TeamHomeOrAway DEFAULT_TEAM = TeamHomeOrAway.HOME;
    private static final TeamHomeOrAway UPDATED_TEAM = TeamHomeOrAway.AWAY;

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Instant DEFAULT_TIME_OF_EVENT_OCCURENCE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_OF_EVENT_OCCURENCE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private GameEventRepository gameEventRepository;

    @Autowired
    private GameEventMapper gameEventMapper;

    @Autowired
    private GameEventService gameEventService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGameEventMockMvc;

    private GameEvent gameEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GameEvent createEntity(EntityManager em) {
        GameEvent gameEvent = new GameEvent()
            .timeOfMessage(DEFAULT_TIME_OF_MESSAGE)
            .gameEventId(DEFAULT_GAME_EVENT_ID)
            .eventType(DEFAULT_EVENT_TYPE)
            .team(DEFAULT_TEAM)
            .active(DEFAULT_ACTIVE)
            .timeOfEventOccurence(DEFAULT_TIME_OF_EVENT_OCCURENCE);
        return gameEvent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GameEvent createUpdatedEntity(EntityManager em) {
        GameEvent gameEvent = new GameEvent()
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .gameEventId(UPDATED_GAME_EVENT_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .team(UPDATED_TEAM)
            .active(UPDATED_ACTIVE)
            .timeOfEventOccurence(UPDATED_TIME_OF_EVENT_OCCURENCE);
        return gameEvent;
    }

    @BeforeEach
    public void initTest() {
        gameEvent = createEntity(em);
    }

    @Test
    @Transactional
    public void createGameEvent() throws Exception {
        int databaseSizeBeforeCreate = gameEventRepository.findAll().size();
        // Create the GameEvent
        GameEventDTO gameEventDTO = gameEventMapper.toDto(gameEvent);
        restGameEventMockMvc.perform(post("/api/game-events").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameEventDTO)))
            .andExpect(status().isCreated());

        // Validate the GameEvent in the database
        List<GameEvent> gameEventList = gameEventRepository.findAll();
        assertThat(gameEventList).hasSize(databaseSizeBeforeCreate + 1);
        GameEvent testGameEvent = gameEventList.get(gameEventList.size() - 1);
        assertThat(testGameEvent.getTimeOfMessage()).isEqualTo(DEFAULT_TIME_OF_MESSAGE);
        assertThat(testGameEvent.getGameEventId()).isEqualTo(DEFAULT_GAME_EVENT_ID);
        assertThat(testGameEvent.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
        assertThat(testGameEvent.getTeam()).isEqualTo(DEFAULT_TEAM);
        assertThat(testGameEvent.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testGameEvent.getTimeOfEventOccurence()).isEqualTo(DEFAULT_TIME_OF_EVENT_OCCURENCE);
    }

    @Test
    @Transactional
    public void createGameEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gameEventRepository.findAll().size();

        // Create the GameEvent with an existing ID
        gameEvent.setId(1L);
        GameEventDTO gameEventDTO = gameEventMapper.toDto(gameEvent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGameEventMockMvc.perform(post("/api/game-events").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GameEvent in the database
        List<GameEvent> gameEventList = gameEventRepository.findAll();
        assertThat(gameEventList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGameEvents() throws Exception {
        // Initialize the database
        gameEventRepository.saveAndFlush(gameEvent);

        // Get all the gameEventList
        restGameEventMockMvc.perform(get("/api/game-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gameEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeOfMessage").value(hasItem(DEFAULT_TIME_OF_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].gameEventId").value(hasItem(DEFAULT_GAME_EVENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].team").value(hasItem(DEFAULT_TEAM.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].timeOfEventOccurence").value(hasItem(DEFAULT_TIME_OF_EVENT_OCCURENCE.toString())));
    }
    
    @Test
    @Transactional
    public void getGameEvent() throws Exception {
        // Initialize the database
        gameEventRepository.saveAndFlush(gameEvent);

        // Get the gameEvent
        restGameEventMockMvc.perform(get("/api/game-events/{id}", gameEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gameEvent.getId().intValue()))
            .andExpect(jsonPath("$.timeOfMessage").value(DEFAULT_TIME_OF_MESSAGE.toString()))
            .andExpect(jsonPath("$.gameEventId").value(DEFAULT_GAME_EVENT_ID.intValue()))
            .andExpect(jsonPath("$.eventType").value(DEFAULT_EVENT_TYPE.toString()))
            .andExpect(jsonPath("$.team").value(DEFAULT_TEAM.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.timeOfEventOccurence").value(DEFAULT_TIME_OF_EVENT_OCCURENCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGameEvent() throws Exception {
        // Get the gameEvent
        restGameEventMockMvc.perform(get("/api/game-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGameEvent() throws Exception {
        // Initialize the database
        gameEventRepository.saveAndFlush(gameEvent);

        int databaseSizeBeforeUpdate = gameEventRepository.findAll().size();

        // Update the gameEvent
        GameEvent updatedGameEvent = gameEventRepository.findById(gameEvent.getId()).get();
        // Disconnect from session so that the updates on updatedGameEvent are not directly saved in db
        em.detach(updatedGameEvent);
        updatedGameEvent
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .gameEventId(UPDATED_GAME_EVENT_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .team(UPDATED_TEAM)
            .active(UPDATED_ACTIVE)
            .timeOfEventOccurence(UPDATED_TIME_OF_EVENT_OCCURENCE);
        GameEventDTO gameEventDTO = gameEventMapper.toDto(updatedGameEvent);

        restGameEventMockMvc.perform(put("/api/game-events").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameEventDTO)))
            .andExpect(status().isOk());

        // Validate the GameEvent in the database
        List<GameEvent> gameEventList = gameEventRepository.findAll();
        assertThat(gameEventList).hasSize(databaseSizeBeforeUpdate);
        GameEvent testGameEvent = gameEventList.get(gameEventList.size() - 1);
        assertThat(testGameEvent.getTimeOfMessage()).isEqualTo(UPDATED_TIME_OF_MESSAGE);
        assertThat(testGameEvent.getGameEventId()).isEqualTo(UPDATED_GAME_EVENT_ID);
        assertThat(testGameEvent.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
        assertThat(testGameEvent.getTeam()).isEqualTo(UPDATED_TEAM);
        assertThat(testGameEvent.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testGameEvent.getTimeOfEventOccurence()).isEqualTo(UPDATED_TIME_OF_EVENT_OCCURENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingGameEvent() throws Exception {
        int databaseSizeBeforeUpdate = gameEventRepository.findAll().size();

        // Create the GameEvent
        GameEventDTO gameEventDTO = gameEventMapper.toDto(gameEvent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGameEventMockMvc.perform(put("/api/game-events").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GameEvent in the database
        List<GameEvent> gameEventList = gameEventRepository.findAll();
        assertThat(gameEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGameEvent() throws Exception {
        // Initialize the database
        gameEventRepository.saveAndFlush(gameEvent);

        int databaseSizeBeforeDelete = gameEventRepository.findAll().size();

        // Delete the gameEvent
        restGameEventMockMvc.perform(delete("/api/game-events/{id}", gameEvent.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GameEvent> gameEventList = gameEventRepository.findAll();
        assertThat(gameEventList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
