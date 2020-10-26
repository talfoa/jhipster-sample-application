package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.ScoreBoard;
import com.mycompany.myapp.repository.ScoreBoardRepository;
import com.mycompany.myapp.service.ScoreBoardService;
import com.mycompany.myapp.service.dto.ScoreBoardDTO;
import com.mycompany.myapp.service.mapper.ScoreBoardMapper;

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
 * Integration tests for the {@link ScoreBoardResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ScoreBoardResourceIT {

    private static final Instant DEFAULT_TIME_OF_MESSAGE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_OF_MESSAGE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_GAME_PART = "AAAAAAAAAA";
    private static final String UPDATED_GAME_PART = "BBBBBBBBBB";

    private static final String DEFAULT_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_SCORE_PART = "AAAAAAAAAA";
    private static final String UPDATED_SCORE_PART = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HIDDEN = false;
    private static final Boolean UPDATED_HIDDEN = true;

    private static final Boolean DEFAULT_HIDE_TIMER = false;
    private static final Boolean UPDATED_HIDE_TIMER = true;

    private static final Integer DEFAULT_REMAINING_TIME_IN_PERIOD = 1;
    private static final Integer UPDATED_REMAINING_TIME_IN_PERIOD = 2;

    private static final Integer DEFAULT_RELATIVE_PLAYER_COUNT = 1;
    private static final Integer UPDATED_RELATIVE_PLAYER_COUNT = 2;

    @Autowired
    private ScoreBoardRepository scoreBoardRepository;

    @Autowired
    private ScoreBoardMapper scoreBoardMapper;

    @Autowired
    private ScoreBoardService scoreBoardService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restScoreBoardMockMvc;

    private ScoreBoard scoreBoard;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ScoreBoard createEntity(EntityManager em) {
        ScoreBoard scoreBoard = new ScoreBoard()
            .timeOfMessage(DEFAULT_TIME_OF_MESSAGE)
            .gamePart(DEFAULT_GAME_PART)
            .score(DEFAULT_SCORE)
            .scorePart(DEFAULT_SCORE_PART)
            .hidden(DEFAULT_HIDDEN)
            .hideTimer(DEFAULT_HIDE_TIMER)
            .remainingTimeInPeriod(DEFAULT_REMAINING_TIME_IN_PERIOD)
            .relativePlayerCount(DEFAULT_RELATIVE_PLAYER_COUNT);
        return scoreBoard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ScoreBoard createUpdatedEntity(EntityManager em) {
        ScoreBoard scoreBoard = new ScoreBoard()
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .gamePart(UPDATED_GAME_PART)
            .score(UPDATED_SCORE)
            .scorePart(UPDATED_SCORE_PART)
            .hidden(UPDATED_HIDDEN)
            .hideTimer(UPDATED_HIDE_TIMER)
            .remainingTimeInPeriod(UPDATED_REMAINING_TIME_IN_PERIOD)
            .relativePlayerCount(UPDATED_RELATIVE_PLAYER_COUNT);
        return scoreBoard;
    }

    @BeforeEach
    public void initTest() {
        scoreBoard = createEntity(em);
    }

    @Test
    @Transactional
    public void createScoreBoard() throws Exception {
        int databaseSizeBeforeCreate = scoreBoardRepository.findAll().size();
        // Create the ScoreBoard
        ScoreBoardDTO scoreBoardDTO = scoreBoardMapper.toDto(scoreBoard);
        restScoreBoardMockMvc.perform(post("/api/score-boards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(scoreBoardDTO)))
            .andExpect(status().isCreated());

        // Validate the ScoreBoard in the database
        List<ScoreBoard> scoreBoardList = scoreBoardRepository.findAll();
        assertThat(scoreBoardList).hasSize(databaseSizeBeforeCreate + 1);
        ScoreBoard testScoreBoard = scoreBoardList.get(scoreBoardList.size() - 1);
        assertThat(testScoreBoard.getTimeOfMessage()).isEqualTo(DEFAULT_TIME_OF_MESSAGE);
        assertThat(testScoreBoard.getGamePart()).isEqualTo(DEFAULT_GAME_PART);
        assertThat(testScoreBoard.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testScoreBoard.getScorePart()).isEqualTo(DEFAULT_SCORE_PART);
        assertThat(testScoreBoard.isHidden()).isEqualTo(DEFAULT_HIDDEN);
        assertThat(testScoreBoard.isHideTimer()).isEqualTo(DEFAULT_HIDE_TIMER);
        assertThat(testScoreBoard.getRemainingTimeInPeriod()).isEqualTo(DEFAULT_REMAINING_TIME_IN_PERIOD);
        assertThat(testScoreBoard.getRelativePlayerCount()).isEqualTo(DEFAULT_RELATIVE_PLAYER_COUNT);
    }

    @Test
    @Transactional
    public void createScoreBoardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = scoreBoardRepository.findAll().size();

        // Create the ScoreBoard with an existing ID
        scoreBoard.setId(1L);
        ScoreBoardDTO scoreBoardDTO = scoreBoardMapper.toDto(scoreBoard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restScoreBoardMockMvc.perform(post("/api/score-boards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(scoreBoardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ScoreBoard in the database
        List<ScoreBoard> scoreBoardList = scoreBoardRepository.findAll();
        assertThat(scoreBoardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllScoreBoards() throws Exception {
        // Initialize the database
        scoreBoardRepository.saveAndFlush(scoreBoard);

        // Get all the scoreBoardList
        restScoreBoardMockMvc.perform(get("/api/score-boards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scoreBoard.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeOfMessage").value(hasItem(DEFAULT_TIME_OF_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].gamePart").value(hasItem(DEFAULT_GAME_PART)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].scorePart").value(hasItem(DEFAULT_SCORE_PART)))
            .andExpect(jsonPath("$.[*].hidden").value(hasItem(DEFAULT_HIDDEN.booleanValue())))
            .andExpect(jsonPath("$.[*].hideTimer").value(hasItem(DEFAULT_HIDE_TIMER.booleanValue())))
            .andExpect(jsonPath("$.[*].remainingTimeInPeriod").value(hasItem(DEFAULT_REMAINING_TIME_IN_PERIOD)))
            .andExpect(jsonPath("$.[*].relativePlayerCount").value(hasItem(DEFAULT_RELATIVE_PLAYER_COUNT)));
    }
    
    @Test
    @Transactional
    public void getScoreBoard() throws Exception {
        // Initialize the database
        scoreBoardRepository.saveAndFlush(scoreBoard);

        // Get the scoreBoard
        restScoreBoardMockMvc.perform(get("/api/score-boards/{id}", scoreBoard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(scoreBoard.getId().intValue()))
            .andExpect(jsonPath("$.timeOfMessage").value(DEFAULT_TIME_OF_MESSAGE.toString()))
            .andExpect(jsonPath("$.gamePart").value(DEFAULT_GAME_PART))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.scorePart").value(DEFAULT_SCORE_PART))
            .andExpect(jsonPath("$.hidden").value(DEFAULT_HIDDEN.booleanValue()))
            .andExpect(jsonPath("$.hideTimer").value(DEFAULT_HIDE_TIMER.booleanValue()))
            .andExpect(jsonPath("$.remainingTimeInPeriod").value(DEFAULT_REMAINING_TIME_IN_PERIOD))
            .andExpect(jsonPath("$.relativePlayerCount").value(DEFAULT_RELATIVE_PLAYER_COUNT));
    }
    @Test
    @Transactional
    public void getNonExistingScoreBoard() throws Exception {
        // Get the scoreBoard
        restScoreBoardMockMvc.perform(get("/api/score-boards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScoreBoard() throws Exception {
        // Initialize the database
        scoreBoardRepository.saveAndFlush(scoreBoard);

        int databaseSizeBeforeUpdate = scoreBoardRepository.findAll().size();

        // Update the scoreBoard
        ScoreBoard updatedScoreBoard = scoreBoardRepository.findById(scoreBoard.getId()).get();
        // Disconnect from session so that the updates on updatedScoreBoard are not directly saved in db
        em.detach(updatedScoreBoard);
        updatedScoreBoard
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .gamePart(UPDATED_GAME_PART)
            .score(UPDATED_SCORE)
            .scorePart(UPDATED_SCORE_PART)
            .hidden(UPDATED_HIDDEN)
            .hideTimer(UPDATED_HIDE_TIMER)
            .remainingTimeInPeriod(UPDATED_REMAINING_TIME_IN_PERIOD)
            .relativePlayerCount(UPDATED_RELATIVE_PLAYER_COUNT);
        ScoreBoardDTO scoreBoardDTO = scoreBoardMapper.toDto(updatedScoreBoard);

        restScoreBoardMockMvc.perform(put("/api/score-boards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(scoreBoardDTO)))
            .andExpect(status().isOk());

        // Validate the ScoreBoard in the database
        List<ScoreBoard> scoreBoardList = scoreBoardRepository.findAll();
        assertThat(scoreBoardList).hasSize(databaseSizeBeforeUpdate);
        ScoreBoard testScoreBoard = scoreBoardList.get(scoreBoardList.size() - 1);
        assertThat(testScoreBoard.getTimeOfMessage()).isEqualTo(UPDATED_TIME_OF_MESSAGE);
        assertThat(testScoreBoard.getGamePart()).isEqualTo(UPDATED_GAME_PART);
        assertThat(testScoreBoard.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testScoreBoard.getScorePart()).isEqualTo(UPDATED_SCORE_PART);
        assertThat(testScoreBoard.isHidden()).isEqualTo(UPDATED_HIDDEN);
        assertThat(testScoreBoard.isHideTimer()).isEqualTo(UPDATED_HIDE_TIMER);
        assertThat(testScoreBoard.getRemainingTimeInPeriod()).isEqualTo(UPDATED_REMAINING_TIME_IN_PERIOD);
        assertThat(testScoreBoard.getRelativePlayerCount()).isEqualTo(UPDATED_RELATIVE_PLAYER_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingScoreBoard() throws Exception {
        int databaseSizeBeforeUpdate = scoreBoardRepository.findAll().size();

        // Create the ScoreBoard
        ScoreBoardDTO scoreBoardDTO = scoreBoardMapper.toDto(scoreBoard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScoreBoardMockMvc.perform(put("/api/score-boards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(scoreBoardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ScoreBoard in the database
        List<ScoreBoard> scoreBoardList = scoreBoardRepository.findAll();
        assertThat(scoreBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScoreBoard() throws Exception {
        // Initialize the database
        scoreBoardRepository.saveAndFlush(scoreBoard);

        int databaseSizeBeforeDelete = scoreBoardRepository.findAll().size();

        // Delete the scoreBoard
        restScoreBoardMockMvc.perform(delete("/api/score-boards/{id}", scoreBoard.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ScoreBoard> scoreBoardList = scoreBoardRepository.findAll();
        assertThat(scoreBoardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
