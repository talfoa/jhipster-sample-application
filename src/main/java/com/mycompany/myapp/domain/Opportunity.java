package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Opportunity.
 */
@Entity
@Table(name = "opportunity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Opportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "time_of_message")
    private Instant timeOfMessage;

    @Column(name = "opportunity_id")
    private Long opportunityId;

    @Column(name = "type")
    private String type;

    @Column(name = "handicap")
    private String handicap;

    @Column(name = "line")
    private String line;

    @Column(name = "sequence")
    private String sequence;

    @Column(name = "trading_status")
    private Integer tradingStatus;

    @Column(name = "actual_trading_time")
    private Instant actualTradingTime;

    @Column(name = "note")
    private String note;

    @Column(name = "bet_stop")
    private Integer betStop;

    @Column(name = "results")
    private String results;

    @ManyToOne
    @JsonIgnoreProperties(value = "opportunities", allowSetters = true)
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

    public Opportunity timeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
        return this;
    }

    public void setTimeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public Opportunity opportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
        return this;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getType() {
        return type;
    }

    public Opportunity type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHandicap() {
        return handicap;
    }

    public Opportunity handicap(String handicap) {
        this.handicap = handicap;
        return this;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public String getLine() {
        return line;
    }

    public Opportunity line(String line) {
        this.line = line;
        return this;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSequence() {
        return sequence;
    }

    public Opportunity sequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Integer getTradingStatus() {
        return tradingStatus;
    }

    public Opportunity tradingStatus(Integer tradingStatus) {
        this.tradingStatus = tradingStatus;
        return this;
    }

    public void setTradingStatus(Integer tradingStatus) {
        this.tradingStatus = tradingStatus;
    }

    public Instant getActualTradingTime() {
        return actualTradingTime;
    }

    public Opportunity actualTradingTime(Instant actualTradingTime) {
        this.actualTradingTime = actualTradingTime;
        return this;
    }

    public void setActualTradingTime(Instant actualTradingTime) {
        this.actualTradingTime = actualTradingTime;
    }

    public String getNote() {
        return note;
    }

    public Opportunity note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getBetStop() {
        return betStop;
    }

    public Opportunity betStop(Integer betStop) {
        this.betStop = betStop;
        return this;
    }

    public void setBetStop(Integer betStop) {
        this.betStop = betStop;
    }

    public String getResults() {
        return results;
    }

    public Opportunity results(String results) {
        this.results = results;
        return this;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Match getMatch() {
        return match;
    }

    public Opportunity match(Match match) {
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
        if (!(o instanceof Opportunity)) {
            return false;
        }
        return id != null && id.equals(((Opportunity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Opportunity{" +
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
            "}";
    }
}
