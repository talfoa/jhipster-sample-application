package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MatchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Match} and its DTO {@link MatchDTO}.
 */
@Mapper(componentModel = "spring", uses = {SportMapper.class, RegionMapper.class, LeagueMapper.class, TeamMapper.class})
public interface MatchMapper extends EntityMapper<MatchDTO, Match> {

    @Mapping(source = "sport.id", target = "sportId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "league.id", target = "leagueId")
    @Mapping(source = "homeTeam.id", target = "homeTeamId")
    @Mapping(source = "awayTeam.id", target = "awayTeamId")
    MatchDTO toDto(Match match);

    @Mapping(source = "sportId", target = "sport")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "leagueId", target = "league")
    @Mapping(source = "homeTeamId", target = "homeTeam")
    @Mapping(source = "awayTeamId", target = "awayTeam")
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
