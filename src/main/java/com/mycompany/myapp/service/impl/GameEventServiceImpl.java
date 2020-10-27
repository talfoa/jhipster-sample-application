package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.GameEventService;
import com.mycompany.myapp.domain.GameEvent;
import com.mycompany.myapp.repository.GameEventRepository;
import com.mycompany.myapp.service.dto.GameEventDTO;
import com.mycompany.myapp.service.mapper.GameEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link GameEvent}.
 */
@Service
@Transactional
public class GameEventServiceImpl implements GameEventService {

    private final Logger log = LoggerFactory.getLogger(GameEventServiceImpl.class);

    private final GameEventRepository gameEventRepository;

    private final GameEventMapper gameEventMapper;

    public GameEventServiceImpl(GameEventRepository gameEventRepository, GameEventMapper gameEventMapper) {
        this.gameEventRepository = gameEventRepository;
        this.gameEventMapper = gameEventMapper;
    }

    @Override
    public GameEventDTO save(GameEventDTO gameEventDTO) {
        log.debug("Request to save GameEvent : {}", gameEventDTO);
        GameEvent gameEvent = gameEventMapper.toEntity(gameEventDTO);
        gameEvent = gameEventRepository.save(gameEvent);
        return gameEventMapper.toDto(gameEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameEventDTO> findAll() {
        log.debug("Request to get all GameEvents");
        return gameEventRepository.findAll().stream()
            .map(gameEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GameEventDTO> findOne(Long id) {
        log.debug("Request to get GameEvent : {}", id);
        return gameEventRepository.findById(id)
            .map(gameEventMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GameEvent : {}", id);
        gameEventRepository.deleteById(id);
    }
}
