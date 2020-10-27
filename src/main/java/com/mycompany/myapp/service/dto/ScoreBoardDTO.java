package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ScoreBoard} entity.
 */
public class ScoreBoardDTO implements Serializable {
    
    private Long id;

    private Instant timeOfMessage;

    private String gamePart;

    private String score;

    private String scorePart;

    private Boolean hidden;

    private Boolean hideTimer;

    private Integer remainingTimeInPeriod;

    private Integer relativePlayerCount;


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

    public String getGamePart() {
        return gamePart;
    }

    public void setGamePart(String gamePart) {
        this.gamePart = gamePart;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScorePart() {
        return scorePart;
    }

    public void setScorePart(String scorePart) {
        this.scorePart = scorePart;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean isHideTimer() {
        return hideTimer;
    }

    public void setHideTimer(Boolean hideTimer) {
        this.hideTimer = hideTimer;
    }

    public Integer getRemainingTimeInPeriod() {
        return remainingTimeInPeriod;
    }

    public void setRemainingTimeInPeriod(Integer remainingTimeInPeriod) {
        this.remainingTimeInPeriod = remainingTimeInPeriod;
    }

    public Integer getRelativePlayerCount() {
        return relativePlayerCount;
    }

    public void setRelativePlayerCount(Integer relativePlayerCount) {
        this.relativePlayerCount = relativePlayerCount;
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
        if (!(o instanceof ScoreBoardDTO)) {
            return false;
        }

        return id != null && id.equals(((ScoreBoardDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScoreBoardDTO{" +
            "id=" + getId() +
            ", timeOfMessage='" + getTimeOfMessage() + "'" +
            ", gamePart='" + getGamePart() + "'" +
            ", score='" + getScore() + "'" +
            ", scorePart='" + getScorePart() + "'" +
            ", hidden='" + isHidden() + "'" +
            ", hideTimer='" + isHideTimer() + "'" +
            ", remainingTimeInPeriod=" + getRemainingTimeInPeriod() +
            ", relativePlayerCount=" + getRelativePlayerCount() +
            ", matchId=" + getMatchId() +
            "}";
    }
}
