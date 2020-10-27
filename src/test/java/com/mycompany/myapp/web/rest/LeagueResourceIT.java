package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.League;
import com.mycompany.myapp.repository.LeagueRepository;
import com.mycompany.myapp.service.LeagueService;
import com.mycompany.myapp.service.dto.LeagueDTO;
import com.mycompany.myapp.service.mapper.LeagueMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LeagueResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LeagueResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private LeagueMapper leagueMapper;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeagueMockMvc;

    private League league;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static League createEntity(EntityManager em) {
        League league = new League()
            .name(DEFAULT_NAME);
        return league;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static League createUpdatedEntity(EntityManager em) {
        League league = new League()
            .name(UPDATED_NAME);
        return league;
    }

    @BeforeEach
    public void initTest() {
        league = createEntity(em);
    }

    @Test
    @Transactional
    public void createLeague() throws Exception {
        int databaseSizeBeforeCreate = leagueRepository.findAll().size();
        // Create the League
        LeagueDTO leagueDTO = leagueMapper.toDto(league);
        restLeagueMockMvc.perform(post("/api/leagues").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leagueDTO)))
            .andExpect(status().isCreated());

        // Validate the League in the database
        List<League> leagueList = leagueRepository.findAll();
        assertThat(leagueList).hasSize(databaseSizeBeforeCreate + 1);
        League testLeague = leagueList.get(leagueList.size() - 1);
        assertThat(testLeague.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createLeagueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leagueRepository.findAll().size();

        // Create the League with an existing ID
        league.setId(1L);
        LeagueDTO leagueDTO = leagueMapper.toDto(league);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeagueMockMvc.perform(post("/api/leagues").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leagueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the League in the database
        List<League> leagueList = leagueRepository.findAll();
        assertThat(leagueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = leagueRepository.findAll().size();
        // set the field null
        league.setName(null);

        // Create the League, which fails.
        LeagueDTO leagueDTO = leagueMapper.toDto(league);


        restLeagueMockMvc.perform(post("/api/leagues").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leagueDTO)))
            .andExpect(status().isBadRequest());

        List<League> leagueList = leagueRepository.findAll();
        assertThat(leagueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLeagues() throws Exception {
        // Initialize the database
        leagueRepository.saveAndFlush(league);

        // Get all the leagueList
        restLeagueMockMvc.perform(get("/api/leagues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(league.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getLeague() throws Exception {
        // Initialize the database
        leagueRepository.saveAndFlush(league);

        // Get the league
        restLeagueMockMvc.perform(get("/api/leagues/{id}", league.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(league.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingLeague() throws Exception {
        // Get the league
        restLeagueMockMvc.perform(get("/api/leagues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLeague() throws Exception {
        // Initialize the database
        leagueRepository.saveAndFlush(league);

        int databaseSizeBeforeUpdate = leagueRepository.findAll().size();

        // Update the league
        League updatedLeague = leagueRepository.findById(league.getId()).get();
        // Disconnect from session so that the updates on updatedLeague are not directly saved in db
        em.detach(updatedLeague);
        updatedLeague
            .name(UPDATED_NAME);
        LeagueDTO leagueDTO = leagueMapper.toDto(updatedLeague);

        restLeagueMockMvc.perform(put("/api/leagues").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leagueDTO)))
            .andExpect(status().isOk());

        // Validate the League in the database
        List<League> leagueList = leagueRepository.findAll();
        assertThat(leagueList).hasSize(databaseSizeBeforeUpdate);
        League testLeague = leagueList.get(leagueList.size() - 1);
        assertThat(testLeague.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingLeague() throws Exception {
        int databaseSizeBeforeUpdate = leagueRepository.findAll().size();

        // Create the League
        LeagueDTO leagueDTO = leagueMapper.toDto(league);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeagueMockMvc.perform(put("/api/leagues").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leagueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the League in the database
        List<League> leagueList = leagueRepository.findAll();
        assertThat(leagueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLeague() throws Exception {
        // Initialize the database
        leagueRepository.saveAndFlush(league);

        int databaseSizeBeforeDelete = leagueRepository.findAll().size();

        // Delete the league
        restLeagueMockMvc.perform(delete("/api/leagues/{id}", league.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<League> leagueList = leagueRepository.findAll();
        assertThat(leagueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
