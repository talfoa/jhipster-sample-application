package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ScoreBoard.
 */
@Entity
@Table(name = "score_board")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ScoreBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "time_of_message")
    private Instant timeOfMessage;

    @Column(name = "game_part")
    private String gamePart;

    @Column(name = "score")
    private String score;

    @Column(name = "score_part")
    private String scorePart;

    @Column(name = "hidden")
    private Boolean hidden;

    @Column(name = "hide_timer")
    private Boolean hideTimer;

    @Column(name = "remaining_time_in_period")
    private Integer remainingTimeInPeriod;

    @Column(name = "relative_player_count")
    private Integer relativePlayerCount;

    @ManyToOne
    @JsonIgnoreProperties(value = "scoreBoards", allowSetters = true)
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

    public ScoreBoard timeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
        return this;
    }

    public void setTimeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public String getGamePart() {
        return gamePart;
    }

    public ScoreBoard gamePart(String gamePart) {
        this.gamePart = gamePart;
        return this;
    }

    public void setGamePart(String gamePart) {
        this.gamePart = gamePart;
    }

    public String getScore() {
        return score;
    }

    public ScoreBoard score(String score) {
        this.score = score;
        return this;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScorePart() {
        return scorePart;
    }

    public ScoreBoard scorePart(String scorePart) {
        this.scorePart = scorePart;
        return this;
    }

    public void setScorePart(String scorePart) {
        this.scorePart = scorePart;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public ScoreBoard hidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean isHideTimer() {
        return hideTimer;
    }

    public ScoreBoard hideTimer(Boolean hideTimer) {
        this.hideTimer = hideTimer;
        return this;
    }

    public void setHideTimer(Boolean hideTimer) {
        this.hideTimer = hideTimer;
    }

    public Integer getRemainingTimeInPeriod() {
        return remainingTimeInPeriod;
    }

    public ScoreBoard remainingTimeInPeriod(Integer remainingTimeInPeriod) {
        this.remainingTimeInPeriod = remainingTimeInPeriod;
        return this;
    }

    public void setRemainingTimeInPeriod(Integer remainingTimeInPeriod) {
        this.remainingTimeInPeriod = remainingTimeInPeriod;
    }

    public Integer getRelativePlayerCount() {
        return relativePlayerCount;
    }

    public ScoreBoard relativePlayerCount(Integer relativePlayerCount) {
        this.relativePlayerCount = relativePlayerCount;
        return this;
    }

    public void setRelativePlayerCount(Integer relativePlayerCount) {
        this.relativePlayerCount = relativePlayerCount;
    }

    public Match getMatch() {
        return match;
    }

    public ScoreBoard match(Match match) {
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
        if (!(o instanceof ScoreBoard)) {
            return false;
        }
        return id != null && id.equals(((ScoreBoard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScoreBoard{" +
            "id=" + getId() +
            ", timeOfMessage='" + getTimeOfMessage() + "'" +
            ", gamePart='" + getGamePart() + "'" +
            ", score='" + getScore() + "'" +
            ", scorePart='" + getScorePart() + "'" +
            ", hidden='" + isHidden() + "'" +
            ", hideTimer='" + isHideTimer() + "'" +
            ", remainingTimeInPeriod=" + getRemainingTimeInPeriod() +
            ", relativePlayerCount=" + getRelativePlayerCount() +
            "}";
    }
}
