package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ScoreBoardTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScoreBoard.class);
        ScoreBoard scoreBoard1 = new ScoreBoard();
        scoreBoard1.setId(1L);
        ScoreBoard scoreBoard2 = new ScoreBoard();
        scoreBoard2.setId(scoreBoard1.getId());
        assertThat(scoreBoard1).isEqualTo(scoreBoard2);
        scoreBoard2.setId(2L);
        assertThat(scoreBoard1).isNotEqualTo(scoreBoard2);
        scoreBoard1.setId(null);
        assertThat(scoreBoard1).isNotEqualTo(scoreBoard2);
    }
}
