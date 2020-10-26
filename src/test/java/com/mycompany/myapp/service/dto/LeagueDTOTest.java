package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class LeagueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeagueDTO.class);
        LeagueDTO leagueDTO1 = new LeagueDTO();
        leagueDTO1.setId(1L);
        LeagueDTO leagueDTO2 = new LeagueDTO();
        assertThat(leagueDTO1).isNotEqualTo(leagueDTO2);
        leagueDTO2.setId(leagueDTO1.getId());
        assertThat(leagueDTO1).isEqualTo(leagueDTO2);
        leagueDTO2.setId(2L);
        assertThat(leagueDTO1).isNotEqualTo(leagueDTO2);
        leagueDTO1.setId(null);
        assertThat(leagueDTO1).isNotEqualTo(leagueDTO2);
    }
}
