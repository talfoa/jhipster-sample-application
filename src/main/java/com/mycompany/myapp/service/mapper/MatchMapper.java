package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MatchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Match} and its DTO {@link MatchDTO}.
 */
@Mapper(componentModel = "spring", uses = {TeamMapper.class, LeagueMapper.class, SportMapper.class, RegionMapper.class})
public interface MatchMapper extends EntityMapper<MatchDTO, Match> {

    @Mapping(source = "homeTeam.id", target = "homeTeamId")
    @Mapping(source = "awayTeam.id", target = "awayTeamId")
    @Mapping(source = "league.id", target = "leagueId")
    @Mapping(source = "sport.id", target = "sportId")
    @Mapping(source = "region.id", target = "regionId")
    MatchDTO toDto(Match match);

    @Mapping(target = "gameEvents", ignore = true)
    @Mapping(target = "removeGameEvent", ignore = true)
    @Mapping(target = "scoreBoards", ignore = true)
    @Mapping(target = "removeScoreBoard", ignore = true)
    @Mapping(target = "opportunities", ignore = true)
    @Mapping(target = "removeOpportunity", ignore = true)
    @Mapping(source = "homeTeamId", target = "homeTeam")
    @Mapping(source = "awayTeamId", target = "awayTeam")
    @Mapping(source = "leagueId", target = "league")
    @Mapping(source = "sportId", target = "sport")
    @Mapping(source = "regionId", target = "region")
    Match toEntity(MatchDTO matchDTO);

    default Match fromId(Long id) {
        if (id == null) {
            return null;
        }
        Match match = new Match();
        match.setId(id);
        return match;
    }
}
