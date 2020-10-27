package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LeagueMapperTest {

    private LeagueMapper leagueMapper;

    @BeforeEach
    public void setUp() {
        leagueMapper = new LeagueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(leagueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(leagueMapper.fromId(null)).isNull();
    }
}
