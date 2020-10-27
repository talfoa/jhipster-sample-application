package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Opportunity;
import com.mycompany.myapp.repository.OpportunityRepository;
import com.mycompany.myapp.service.OpportunityService;
import com.mycompany.myapp.service.dto.OpportunityDTO;
import com.mycompany.myapp.service.mapper.OpportunityMapper;

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
 * Integration tests for the {@link OpportunityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OpportunityResourceIT {

    private static final Instant DEFAULT_TIME_OF_MESSAGE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_OF_MESSAGE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_OPPORTUNITY_ID = 1L;
    private static final Long UPDATED_OPPORTUNITY_ID = 2L;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_HANDICAP = "AAAAAAAAAA";
    private static final String UPDATED_HANDICAP = "BBBBBBBBBB";

    private static final String DEFAULT_LINE = "AAAAAAAAAA";
    private static final String UPDATED_LINE = "BBBBBBBBBB";

    private static final String DEFAULT_SEQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRADING_STATUS = 1;
    private static final Integer UPDATED_TRADING_STATUS = 2;

    private static final Instant DEFAULT_ACTUAL_TRADING_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTUAL_TRADING_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Integer DEFAULT_BET_STOP = 1;
    private static final Integer UPDATED_BET_STOP = 2;

    private static final String DEFAULT_RESULTS = "AAAAAAAAAA";
    private static final String UPDATED_RESULTS = "BBBBBBBBBB";

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OpportunityMapper opportunityMapper;

    @Autowired
    private OpportunityService opportunityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOpportunityMockMvc;

    private Opportunity opportunity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Opportunity createEntity(EntityManager em) {
        Opportunity opportunity = new Opportunity()
            .timeOfMessage(DEFAULT_TIME_OF_MESSAGE)
            .opportunityId(DEFAULT_OPPORTUNITY_ID)
            .type(DEFAULT_TYPE)
            .handicap(DEFAULT_HANDICAP)
            .line(DEFAULT_LINE)
            .sequence(DEFAULT_SEQUENCE)
            .tradingStatus(DEFAULT_TRADING_STATUS)
            .actualTradingTime(DEFAULT_ACTUAL_TRADING_TIME)
            .note(DEFAULT_NOTE)
            .betStop(DEFAULT_BET_STOP)
            .results(DEFAULT_RESULTS);
        return opportunity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Opportunity createUpdatedEntity(EntityManager em) {
        Opportunity opportunity = new Opportunity()
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .opportunityId(UPDATED_OPPORTUNITY_ID)
            .type(UPDATED_TYPE)
            .handicap(UPDATED_HANDICAP)
            .line(UPDATED_LINE)
            .sequence(UPDATED_SEQUENCE)
            .tradingStatus(UPDATED_TRADING_STATUS)
            .actualTradingTime(UPDATED_ACTUAL_TRADING_TIME)
            .note(UPDATED_NOTE)
            .betStop(UPDATED_BET_STOP)
            .results(UPDATED_RESULTS);
        return opportunity;
    }

    @BeforeEach
    public void initTest() {
        opportunity = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpportunity() throws Exception {
        int databaseSizeBeforeCreate = opportunityRepository.findAll().size();
        // Create the Opportunity
        OpportunityDTO opportunityDTO = opportunityMapper.toDto(opportunity);
        restOpportunityMockMvc.perform(post("/api/opportunities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opportunityDTO)))
            .andExpect(status().isCreated());

        // Validate the Opportunity in the database
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertThat(opportunityList).hasSize(databaseSizeBeforeCreate + 1);
        Opportunity testOpportunity = opportunityList.get(opportunityList.size() - 1);
        assertThat(testOpportunity.getTimeOfMessage()).isEqualTo(DEFAULT_TIME_OF_MESSAGE);
        assertThat(testOpportunity.getOpportunityId()).isEqualTo(DEFAULT_OPPORTUNITY_ID);
        assertThat(testOpportunity.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testOpportunity.getHandicap()).isEqualTo(DEFAULT_HANDICAP);
        assertThat(testOpportunity.getLine()).isEqualTo(DEFAULT_LINE);
        assertThat(testOpportunity.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
        assertThat(testOpportunity.getTradingStatus()).isEqualTo(DEFAULT_TRADING_STATUS);
        assertThat(testOpportunity.getActualTradingTime()).isEqualTo(DEFAULT_ACTUAL_TRADING_TIME);
        assertThat(testOpportunity.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testOpportunity.getBetStop()).isEqualTo(DEFAULT_BET_STOP);
        assertThat(testOpportunity.getResults()).isEqualTo(DEFAULT_RESULTS);
    }

    @Test
    @Transactional
    public void createOpportunityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opportunityRepository.findAll().size();

        // Create the Opportunity with an existing ID
        opportunity.setId(1L);
        OpportunityDTO opportunityDTO = opportunityMapper.toDto(opportunity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpportunityMockMvc.perform(post("/api/opportunities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opportunityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Opportunity in the database
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertThat(opportunityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOpportunities() throws Exception {
        // Initialize the database
        opportunityRepository.saveAndFlush(opportunity);

        // Get all the opportunityList
        restOpportunityMockMvc.perform(get("/api/opportunities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opportunity.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeOfMessage").value(hasItem(DEFAULT_TIME_OF_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].opportunityId").value(hasItem(DEFAULT_OPPORTUNITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].handicap").value(hasItem(DEFAULT_HANDICAP)))
            .andExpect(jsonPath("$.[*].line").value(hasItem(DEFAULT_LINE)))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)))
            .andExpect(jsonPath("$.[*].tradingStatus").value(hasItem(DEFAULT_TRADING_STATUS)))
            .andExpect(jsonPath("$.[*].actualTradingTime").value(hasItem(DEFAULT_ACTUAL_TRADING_TIME.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].betStop").value(hasItem(DEFAULT_BET_STOP)))
            .andExpect(jsonPath("$.[*].results").value(hasItem(DEFAULT_RESULTS)));
    }
    
    @Test
    @Transactional
    public void getOpportunity() throws Exception {
        // Initialize the database
        opportunityRepository.saveAndFlush(opportunity);

        // Get the opportunity
        restOpportunityMockMvc.perform(get("/api/opportunities/{id}", opportunity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(opportunity.getId().intValue()))
            .andExpect(jsonPath("$.timeOfMessage").value(DEFAULT_TIME_OF_MESSAGE.toString()))
            .andExpect(jsonPath("$.opportunityId").value(DEFAULT_OPPORTUNITY_ID.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.handicap").value(DEFAULT_HANDICAP))
            .andExpect(jsonPath("$.line").value(DEFAULT_LINE))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE))
            .andExpect(jsonPath("$.tradingStatus").value(DEFAULT_TRADING_STATUS))
            .andExpect(jsonPath("$.actualTradingTime").value(DEFAULT_ACTUAL_TRADING_TIME.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.betStop").value(DEFAULT_BET_STOP))
            .andExpect(jsonPath("$.results").value(DEFAULT_RESULTS));
    }
    @Test
    @Transactional
    public void getNonExistingOpportunity() throws Exception {
        // Get the opportunity
        restOpportunityMockMvc.perform(get("/api/opportunities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpportunity() throws Exception {
        // Initialize the database
        opportunityRepository.saveAndFlush(opportunity);

        int databaseSizeBeforeUpdate = opportunityRepository.findAll().size();

        // Update the opportunity
        Opportunity updatedOpportunity = opportunityRepository.findById(opportunity.getId()).get();
        // Disconnect from session so that the updates on updatedOpportunity are not directly saved in db
        em.detach(updatedOpportunity);
        updatedOpportunity
            .timeOfMessage(UPDATED_TIME_OF_MESSAGE)
            .opportunityId(UPDATED_OPPORTUNITY_ID)
            .type(UPDATED_TYPE)
            .handicap(UPDATED_HANDICAP)
            .line(UPDATED_LINE)
            .sequence(UPDATED_SEQUENCE)
            .tradingStatus(UPDATED_TRADING_STATUS)
            .actualTradingTime(UPDATED_ACTUAL_TRADING_TIME)
            .note(UPDATED_NOTE)
            .betStop(UPDATED_BET_STOP)
            .results(UPDATED_RESULTS);
        OpportunityDTO opportunityDTO = opportunityMapper.toDto(updatedOpportunity);

        restOpportunityMockMvc.perform(put("/api/opportunities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opportunityDTO)))
            .andExpect(status().isOk());

        // Validate the Opportunity in the database
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertThat(opportunityList).hasSize(databaseSizeBeforeUpdate);
        Opportunity testOpportunity = opportunityList.get(opportunityList.size() - 1);
        assertThat(testOpportunity.getTimeOfMessage()).isEqualTo(UPDATED_TIME_OF_MESSAGE);
        assertThat(testOpportunity.getOpportunityId()).isEqualTo(UPDATED_OPPORTUNITY_ID);
        assertThat(testOpportunity.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOpportunity.getHandicap()).isEqualTo(UPDATED_HANDICAP);
        assertThat(testOpportunity.getLine()).isEqualTo(UPDATED_LINE);
        assertThat(testOpportunity.getSequence()).isEqualTo(UPDATED_SEQUENCE);
        assertThat(testOpportunity.getTradingStatus()).isEqualTo(UPDATED_TRADING_STATUS);
        assertThat(testOpportunity.getActualTradingTime()).isEqualTo(UPDATED_ACTUAL_TRADING_TIME);
        assertThat(testOpportunity.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testOpportunity.getBetStop()).isEqualTo(UPDATED_BET_STOP);
        assertThat(testOpportunity.getResults()).isEqualTo(UPDATED_RESULTS);
    }

    @Test
    @Transactional
    public void updateNonExistingOpportunity() throws Exception {
        int databaseSizeBeforeUpdate = opportunityRepository.findAll().size();

        // Create the Opportunity
        OpportunityDTO opportunityDTO = opportunityMapper.toDto(opportunity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpportunityMockMvc.perform(put("/api/opportunities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opportunityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Opportunity in the database
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertThat(opportunityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpportunity() throws Exception {
        // Initialize the database
        opportunityRepository.saveAndFlush(opportunity);

        int databaseSizeBeforeDelete = opportunityRepository.findAll().size();

        // Delete the opportunity
        restOpportunityMockMvc.perform(delete("/api/opportunities/{id}", opportunity.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertThat(opportunityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
