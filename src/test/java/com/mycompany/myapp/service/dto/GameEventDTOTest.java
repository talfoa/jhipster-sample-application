package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class GameEventDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GameEventDTO.class);
        GameEventDTO gameEventDTO1 = new GameEventDTO();
        gameEventDTO1.setId(1L);
        GameEventDTO gameEventDTO2 = new GameEventDTO();
        assertThat(gameEventDTO1).isNotEqualTo(gameEventDTO2);
        gameEventDTO2.setId(gameEventDTO1.getId());
        assertThat(gameEventDTO1).isEqualTo(gameEventDTO2);
        gameEventDTO2.setId(2L);
        assertThat(gameEventDTO1).isNotEqualTo(gameEventDTO2);
        gameEventDTO1.setId(null);
        assertThat(gameEventDTO1).isNotEqualTo(gameEventDTO2);
    }
}
