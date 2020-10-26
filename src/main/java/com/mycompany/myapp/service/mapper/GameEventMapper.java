package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.GameEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GameEvent} and its DTO {@link GameEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {MatchMapper.class})
public interface GameEventMapper extends EntityMapper<GameEventDTO, GameEvent> {

    @Mapping(source = "match.id", target = "matchId")
    GameEventDTO toDto(GameEvent gameEvent);

    @Mapping(source = "matchId", target = "match")
    GameEvent toEntity(GameEventDTO gameEventDTO);

    default GameEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        GameEvent gameEvent = new GameEvent();
        gameEvent.setId(id);
        return gameEvent;
    }
}
