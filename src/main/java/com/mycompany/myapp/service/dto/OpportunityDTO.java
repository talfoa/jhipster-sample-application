package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Opportunity} entity.
 */
public class OpportunityDTO implements Serializable {
    
    private Long id;

    private Instant timeOfMessage;

    private Long opportunityId;

    private String type;

    private String handicap;

    private String line;

    private String sequence;

    private Integer tradingStatus;

    private Instant actualTradingTime;

    private String note;

    private Integer betStop;

    private String results;


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

    public Long getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Integer getTradingStatus() {
        return tradingStatus;
    }

    public void setTradingStatus(Integer tradingStatus) {
        this.tradingStatus = tradingStatus;
    }

    public Instant getActualTradingTime() {
        return actualTradingTime;
    }

    public void setActualTradingTime(Instant actualTradingTime) {
        this.actualTradingTime = actualTradingTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getBetStop() {
        return betStop;
    }

    public void setBetStop(Integer betStop) {
        this.betStop = betStop;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
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
        if (!(o instanceof OpportunityDTO)) {
            return false;
        }

        return id != null && id.equals(((OpportunityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpportunityDTO{" +
            "id=" + getId() +
            ", timeOfMessage='" + getTimeOfMessage() + "'" +
            ", opportunityId=" + getOpportunityId() +
            ", type='" + getType() + "'" +
            ", handicap='" + getHandicap() + "'" +
            ", line='" + getLine() + "'" +
            ", sequence='" + getSequence() + "'" +
            ", tradingStatus=" + getTradingStatus() +
            ", actualTradingTime='" + getActualTradingTime() + "'" +
            ", note='" + getNote() + "'" +
            ", betStop=" + getBetStop() +
            ", results='" + getResults() + "'" +
            ", matchId=" + getMatchId() +
            "}";
    }
}
