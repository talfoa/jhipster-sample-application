package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardMapperTest {

    private ScoreBoardMapper scoreBoardMapper;

    @BeforeEach
    public void setUp() {
        scoreBoardMapper = new ScoreBoardMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(scoreBoardMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(scoreBoardMapper.fromId(null)).isNull();
    }
}
