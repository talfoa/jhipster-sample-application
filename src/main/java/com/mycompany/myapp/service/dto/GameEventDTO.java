package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.mycompany.myapp.domain.enumeration.GameEventType;
import com.mycompany.myapp.domain.enumeration.TeamHomeOrAway;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.GameEvent} entity.
 */
public class GameEventDTO implements Serializable {
    
    private Long id;

    private Instant timeOfMessage;

    private Long gameEventId;

    private GameEventType eventType;

    private TeamHomeOrAway team;

    private Boolean active;

    private Instant timeOfEventOccurence;


    private Long matchId;
    
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

    public Long getGameEventId() {
        return gameEventId;
    }

    public void setGameEventId(Long gameEventId) {
        this.gameEventId = gameEventId;
    }

    public GameEventType getEventType() {
        return eventType;
    }

    public void setEventType(GameEventType eventType) {
        this.eventType = eventType;
    }

    public TeamHomeOrAway getTeam() {
        return team;
    }

    public void setTeam(TeamHomeOrAway team) {
        this.team = team;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getTimeOfEventOccurence() {
        return timeOfEventOccurence;
    }

    public void setTimeOfEventOccurence(Instant timeOfEventOccurence) {
        this.timeOfEventOccurence = timeOfEventOccurence;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameEventDTO)) {
            return false;
        }

        return id != null && id.equals(((GameEventDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GameEventDTO{" +
            "id=" + getId() +
            ", timeOfMessage='" + getTimeOfMessage() + "'" +
            ", gameEventId=" + getGameEventId() +
            ", eventType='" + getEventType() + "'" +
            ", team='" + getTeam() + "'" +
            ", active='" + isActive() + "'" +
            ", timeOfEventOccurence='" + getTimeOfEventOccurence() + "'" +
            ", matchId=" + getMatchId() +
            "}";
    }
}
