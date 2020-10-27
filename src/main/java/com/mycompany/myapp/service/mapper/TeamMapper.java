package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TeamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Team} and its DTO {@link TeamDTO}.
 */
@Mapper(componentModel = "spring", uses = {LeagueMapper.class, SportMapper.class})
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {

    @Mapping(source = "sport.id", target = "sportId")
    TeamDTO toDto(Team team);

    @Mapping(target = "players", ignore = true)
    @Mapping(target = "removePlayer", ignore = true)
    @Mapping(target = "removeLeague", ignore = true)
    @Mapping(source = "sportId", target = "sport")
    Team toEntity(TeamDTO teamDTO);

    default Team fromId(Long id) {
        if (id == null) {
            return null;
        }
        Team team = new Team();
        team.setId(id);
        return team;
    }
}
