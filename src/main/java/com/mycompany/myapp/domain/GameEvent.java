package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.mycompany.myapp.domain.enumeration.GameEventType;

import com.mycompany.myapp.domain.enumeration.TeamHomeOrAway;

/**
 * A GameEvent.
 */
@Entity
@Table(name = "game_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GameEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "time_of_message")
    private Instant timeOfMessage;

    @Column(name = "game_event_id")
    private Long gameEventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private GameEventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(name = "team")
    private TeamHomeOrAway team;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "time_of_event_occurence")
    private Instant timeOfEventOccurence;

    @ManyToOne
    @JsonIgnoreProperties(value = "gameEvents", allowSetters = true)
    private Match match;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimeOfMessage() {
        return timeOfMessage;
    }

    public GameEvent timeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
        return this;
    }

    public void setTimeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public Long getGameEventId() {
        return gameEventId;
    }

    public GameEvent gameEventId(Long gameEventId) {
        this.gameEventId = gameEventId;
        return this;
    }

    public void setGameEventId(Long gameEventId) {
        this.gameEventId = gameEventId;
    }

    public GameEventType getEventType() {
        return eventType;
    }

    public GameEvent eventType(GameEventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public void setEventType(GameEventType eventType) {
        this.eventType = eventType;
    }

    public TeamHomeOrAway getTeam() {
        return team;
    }

    public GameEvent team(TeamHomeOrAway team) {
        this.team = team;
        return this;
    }

    public void setTeam(TeamHomeOrAway team) {
        this.team = team;
    }

    public Boolean isActive() {
        return active;
    }

    public GameEvent active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getTimeOfEventOccurence() {
        return timeOfEventOccurence;
    }

    public GameEvent timeOfEventOccurence(Instant timeOfEventOccurence) {
        this.timeOfEventOccurence = timeOfEventOccurence;
        return this;
    }

    public void setTimeOfEventOccurence(Instant timeOfEventOccurence) {
        this.timeOfEventOccurence = timeOfEventOccurence;
    }

    public Match getMatch() {
        return match;
    }

    public GameEvent match(Match match) {
        this.match = match;
        return this;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameEvent)) {
            return false;
        }
        return id != null && id.equals(((GameEvent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GameEvent{" +
            "id=" + getId() +
            ", timeOfMessage='" + getTimeOfMessage() + "'" +
            ", gameEventId=" + getGameEventId() +
            ", eventType='" + getEventType() + "'" +
            ", team='" + getTeam() + "'" +
            ", active='" + isActive() + "'" +
            ", timeOfEventOccurence='" + getTimeOfEventOccurence() + "'" +
            "}";
    }
}
