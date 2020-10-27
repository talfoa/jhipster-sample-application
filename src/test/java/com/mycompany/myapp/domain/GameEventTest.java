package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class GameEventTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GameEvent.class);
        GameEvent gameEvent1 = new GameEvent();
        gameEvent1.setId(1L);
        GameEvent gameEvent2 = new GameEvent();
        gameEvent2.setId(gameEvent1.getId());
        assertThat(gameEvent1).isEqualTo(gameEvent2);
        gameEvent2.setId(2L);
        assertThat(gameEvent1).isNotEqualTo(gameEvent2);
        gameEvent1.setId(null);
        assertThat(gameEvent1).isNotEqualTo(gameEvent2);
    }
}
