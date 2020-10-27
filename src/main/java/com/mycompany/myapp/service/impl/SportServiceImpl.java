package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SportService;
import com.mycompany.myapp.domain.Sport;
import com.mycompany.myapp.repository.SportRepository;
import com.mycompany.myapp.service.dto.SportDTO;
import com.mycompany.myapp.service.mapper.SportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Sport}.
 */
@Service
@Transactional
public class SportServiceImpl implements SportService {

    private final Logger log = LoggerFactory.getLogger(SportServiceImpl.class);

    private final SportRepository sportRepository;

    private final SportMapper sportMapper;

    public SportServiceImpl(SportRepository sportRepository, SportMapper sportMapper) {
        this.sportRepository = sportRepository;
        this.sportMapper = sportMapper;
    }

    @Override
    public SportDTO save(SportDTO sportDTO) {
        log.debug("Request to save Sport : {}", sportDTO);
        Sport sport = sportMapper.toEntity(sportDTO);
        sport = sportRepository.save(sport);
        return sportMapper.toDto(sport);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SportDTO> findAll() {
        log.debug("Request to get all Sports");
        return sportRepository.findAll().stream()
            .map(sportMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SportDTO> findOne(Long id) {
        log.debug("Request to get Sport : {}", id);
        return sportRepository.findById(id)
            .map(sportMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sport : {}", id);
        sportRepository.deleteById(id);
    }
}
