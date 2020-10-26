package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ScoreBoardService;
import com.mycompany.myapp.domain.ScoreBoard;
import com.mycompany.myapp.repository.ScoreBoardRepository;
import com.mycompany.myapp.service.dto.ScoreBoardDTO;
import com.mycompany.myapp.service.mapper.ScoreBoardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ScoreBoard}.
 */
@Service
@Transactional
public class ScoreBoardServiceImpl implements ScoreBoardService {

    private final Logger log = LoggerFactory.getLogger(ScoreBoardServiceImpl.class);

    private final ScoreBoardRepository scoreBoardRepository;

    private final ScoreBoardMapper scoreBoardMapper;

    public ScoreBoardServiceImpl(ScoreBoardRepository scoreBoardRepository, ScoreBoardMapper scoreBoardMapper) {
        this.scoreBoardRepository = scoreBoardRepository;
        this.scoreBoardMapper = scoreBoardMapper;
    }

    @Override
    public ScoreBoardDTO save(ScoreBoardDTO scoreBoardDTO) {
        log.debug("Request to save ScoreBoard : {}", scoreBoardDTO);
        ScoreBoard scoreBoard = scoreBoardMapper.toEntity(scoreBoardDTO);
        scoreBoard = scoreBoardRepository.save(scoreBoard);
        return scoreBoardMapper.toDto(scoreBoard);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScoreBoardDTO> findAll() {
        log.debug("Request to get all ScoreBoards");
        return scoreBoardRepository.findAll().stream()
            .map(scoreBoardMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ScoreBoardDTO> findOne(Long id) {
        log.debug("Request to get ScoreBoard : {}", id);
        return scoreBoardRepository.findById(id)
            .map(scoreBoardMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ScoreBoard : {}", id);
        scoreBoardRepository.deleteById(id);
    }
}
