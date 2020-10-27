package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Match;
import com.mycompany.myapp.repository.MatchRepository;
import com.mycompany.myapp.service.MatchService;
import com.mycompany.myapp.service.dto.MatchDTO;
import com.mycompany.myapp.service.mapper.MatchMapper;

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

/**
 * Integration tests for the {@link MatchResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MatchResourceIT {

    private static final Instant DEFAULT_TIME_OF_MESSAGE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_OF_MESSAGE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_MATCH_ID = 1L;
    private static final Long UPDATED_MATCH_ID = 2L;

    private static final Instant DEFAULT_MATCH_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MATCH_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_CORNER_SENDING = false;
    private static final Boolean UPDATED_CORNER_SENDING = true;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private MatchService matchService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMatchMockMvc;

    private Match match;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Match createEntity(EntityManager em) {
        Match match = new Match()
            .timeOfMessage(DEFAULT_TIME_OF_MESSAGE)
            .matchId(DEFAULT_MATCH_ID)
            .matchDate(DEFAULT_MATCH_DATE)
            .cornerSending(DEFAULT_CORNER_SENDING);
        return match;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Match createUpdatedEntity(EntityManager em) {
        Match match = new Match()
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .matchId(UPDATED_MATCH_ID)
            .matchDate(UPDATED_MATCH_DATE)
            .cornerSending(UPDATED_CORNER_SENDING);
        return match;
    }

    @BeforeEach
    public void initTest() {
        match = createEntity(em);
    }

    @Test
    @Transactional
    public void createMatch() throws Exception {
        int databaseSizeBeforeCreate = matchRepository.findAll().size();
        // Create the Match
        MatchDTO matchDTO = matchMapper.toDto(match);
        restMatchMockMvc.perform(post("/api/matches").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(matchDTO)))
            .andExpect(status().isCreated());

        // Validate the Match in the database
        List<Match> matchList = matchRepository.findAll();
        assertThat(matchList).hasSize(databaseSizeBeforeCreate + 1);
        Match testMatch = matchList.get(matchList.size() - 1);
        assertThat(testMatch.getTimeOfMessage()).isEqualTo(DEFAULT_TIME_OF_MESSAGE);
        assertThat(testMatch.getMatchId()).isEqualTo(DEFAULT_MATCH_ID);
        assertThat(testMatch.getMatchDate()).isEqualTo(DEFAULT_MATCH_DATE);
        assertThat(testMatch.isCornerSending()).isEqualTo(DEFAULT_CORNER_SENDING);
    }

    @Test
    @Transactional
    public void createMatchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = matchRepository.findAll().size();

        // Create the Match with an existing ID
        match.setId(1L);
        MatchDTO matchDTO = matchMapper.toDto(match);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMatchMockMvc.perform(post("/api/matches").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(matchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Match in the database
        List<Match> matchList = matchRepository.findAll();
        assertThat(matchList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMatches() throws Exception {
        // Initialize the database
        matchRepository.saveAndFlush(match);

        // Get all the matchList
        restMatchMockMvc.perform(get("/api/matches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(match.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeOfMessage").value(hasItem(DEFAULT_TIME_OF_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].matchId").value(hasItem(DEFAULT_MATCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].matchDate").value(hasItem(DEFAULT_MATCH_DATE.toString())))
            .andExpect(jsonPath("$.[*].cornerSending").value(hasItem(DEFAULT_CORNER_SENDING.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getMatch() throws Exception {
        // Initialize the database
        matchRepository.saveAndFlush(match);

        // Get the match
        restMatchMockMvc.perform(get("/api/matches/{id}", match.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(match.getId().intValue()))
            .andExpect(jsonPath("$.timeOfMessage").value(DEFAULT_TIME_OF_MESSAGE.toString()))
            .andExpect(jsonPath("$.matchId").value(DEFAULT_MATCH_ID.intValue()))
            .andExpect(jsonPath("$.matchDate").value(DEFAULT_MATCH_DATE.toString()))
            .andExpect(jsonPath("$.cornerSending").value(DEFAULT_CORNER_SENDING.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMatch() throws Exception {
        // Get the match
        restMatchMockMvc.perform(get("/api/matches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMatch() throws Exception {
        // Initialize the database
        matchRepository.saveAndFlush(match);

        int databaseSizeBeforeUpdate = matchRepository.findAll().size();

        // Update the match
        Match updatedMatch = matchRepository.findById(match.getId()).get();
        // Disconnect from session so that the updates on updatedMatch are not directly saved in db
        em.detach(updatedMatch);
        updatedMatch
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .matchId(UPDATED_MATCH_ID)
            .matchDate(UPDATED_MATCH_DATE)
            .cornerSending(UPDATED_CORNER_SENDING);
        MatchDTO matchDTO = matchMapper.toDto(updatedMatch);

        restMatchMockMvc.perform(put("/api/matches").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(matchDTO)))
            .andExpect(status().isOk());

        // Validate the Match in the database
        List<Match> matchList = matchRepository.findAll();
        assertThat(matchList).hasSize(databaseSizeBeforeUpdate);
        Match testMatch = matchList.get(matchList.size() - 1);
        assertThat(testMatch.getTimeOfMessage()).isEqualTo(UPDATED_TIME_OF_MESSAGE);
        assertThat(testMatch.getMatchId()).isEqualTo(UPDATED_MATCH_ID);
        assertThat(testMatch.getMatchDate()).isEqualTo(UPDATED_MATCH_DATE);
        assertThat(testMatch.isCornerSending()).isEqualTo(UPDATED_CORNER_SENDING);
    }

    @Test
    @Transactional
    public void updateNonExistingMatch() throws Exception {
        int databaseSizeBeforeUpdate = matchRepository.findAll().size();

        // Create the Match
        MatchDTO matchDTO = matchMapper.toDto(match);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMatchMockMvc.perform(put("/api/matches").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(matchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Match in the database
        List<Match> matchList = matchRepository.findAll();
        assertThat(matchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMatch() throws Exception {
        // Initialize the database
        matchRepository.saveAndFlush(match);

        int databaseSizeBeforeDelete = matchRepository.findAll().size();

        // Delete the match
        restMatchMockMvc.perform(delete("/api/matches/{id}", match.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Match> matchList = matchRepository.findAll();
        assertThat(matchList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
