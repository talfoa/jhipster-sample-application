package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LeagueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link League} and its DTO {@link LeagueDTO}.
 */
@Mapper(componentModel = "spring", uses = {SportMapper.class, RegionMapper.class})
public interface LeagueMapper extends EntityMapper<LeagueDTO, League> {

    @Mapping(source = "sport.id", target = "sportId")
    @Mapping(source = "region.id", target = "regionId")
    LeagueDTO toDto(League league);

    @Mapping(source = "sportId", target = "sport")
    @Mapping(source = "regionId", target = "region")
    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "removeTeam", ignore = true)
    League toEntity(LeagueDTO leagueDTO);

    default League fromId(Long id) {
        if (id == null) {
            return null;
        }
        League league = new League();
        league.setId(id);
        return league;
    }
}
