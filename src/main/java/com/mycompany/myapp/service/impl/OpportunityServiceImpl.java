package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.OpportunityService;
import com.mycompany.myapp.domain.Opportunity;
import com.mycompany.myapp.repository.OpportunityRepository;
import com.mycompany.myapp.service.dto.OpportunityDTO;
import com.mycompany.myapp.service.mapper.OpportunityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Opportunity}.
 */
@Service
@Transactional
public class OpportunityServiceImpl implements OpportunityService {

    private final Logger log = LoggerFactory.getLogger(OpportunityServiceImpl.class);

    private final OpportunityRepository opportunityRepository;

    private final OpportunityMapper opportunityMapper;

    public OpportunityServiceImpl(OpportunityRepository opportunityRepository, OpportunityMapper opportunityMapper) {
        this.opportunityRepository = opportunityRepository;
        this.opportunityMapper = opportunityMapper;
    }

    @Override
    public OpportunityDTO save(OpportunityDTO opportunityDTO) {
        log.debug("Request to save Opportunity : {}", opportunityDTO);
        Opportunity opportunity = opportunityMapper.toEntity(opportunityDTO);
        opportunity = opportunityRepository.save(opportunity);
        return opportunityMapper.toDto(opportunity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpportunityDTO> findAll() {
        log.debug("Request to get all Opportunities");
        return opportunityRepository.findAll().stream()
            .map(opportunityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OpportunityDTO> findOne(Long id) {
        log.debug("Request to get Opportunity : {}", id);
        return opportunityRepository.findById(id)
            .map(opportunityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Opportunity : {}", id);
        opportunityRepository.deleteById(id);
    }
}
