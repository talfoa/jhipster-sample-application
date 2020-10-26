package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GameEventMapperTest {

    private GameEventMapper gameEventMapper;

    @BeforeEach
    public void setUp() {
        gameEventMapper = new GameEventMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(gameEventMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gameEventMapper.fromId(null)).isNull();
    }
}
