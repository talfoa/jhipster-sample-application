package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ScoreBoardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ScoreBoard} and its DTO {@link ScoreBoardDTO}.
 */
@Mapper(componentModel = "spring", uses = {MatchMapper.class})
public interface ScoreBoardMapper extends EntityMapper<ScoreBoardDTO, ScoreBoard> {

    @Mapping(source = "match.id", target = "matchId")
    ScoreBoardDTO toDto(ScoreBoard scoreBoard);

    @Mapping(source = "matchId", target = "match")
    ScoreBoard toEntity(ScoreBoardDTO scoreBoardDTO);

    default ScoreBoard fromId(Long id) {
        if (id == null) {
            return null;
        }
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setId(id);
        return scoreBoard;
    }
}
