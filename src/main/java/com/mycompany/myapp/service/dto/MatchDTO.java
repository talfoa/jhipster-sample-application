package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Match} entity.
 */
public class MatchDTO implements Serializable {
    
    private Long id;

    private Instant timeOfMessage;

    private Long matchId;

    private Instant matchDate;

    private Boolean cornerSending;


    private Long sportId;

    private Long regionId;

    private Long leagueId;

    private Long homeTeamId;

    private Long awayTeamId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimeOfMessage() {
        return timeOfMessage;
    }

    public void setTimeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Instant getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Instant matchDate) {
        this.matchDate = matchDate;
    }

    public Boolean isCornerSending() {
        return cornerSending;
    }

    public void setCornerSending(Boolean cornerSending) {
        this.cornerSending = cornerSending;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long teamId) {
        this.homeTeamId = teamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long teamId) {
        this.awayTeamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MatchDTO)) {
            return false;
        }

        return id != null && id.equals(((MatchDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MatchDTO{" +
            "id=" + getId() +
            ", timeOfMessage='" + getTimeOfMessage() + "'" +
            ", matchId=" + getMatchId() +
            ", matchDate='" + getMatchDate() + "'" +
            ", cornerSending='" + isCornerSending() + "'" +
            ", sportId=" + getSportId() +
            ", regionId=" + getRegionId() +
            ", leagueId=" + getLeagueId() +
            ", homeTeamId=" + getHomeTeamId() +
            ", awayTeamId=" + getAwayTeamId() +
            "}";
    }
}
