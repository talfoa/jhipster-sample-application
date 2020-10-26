package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Match.
 */
@Entity
@Table(name = "match")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "time_of_message")
    private Instant timeOfMessage;

    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "match_date")
    private Instant matchDate;

    @Column(name = "corner_sending")
    private Boolean cornerSending;

    @OneToOne
    @JoinColumn(unique = true)
    private Sport sport;

    @OneToOne
    @JoinColumn(unique = true)
    private Region region;

    @OneToOne
    @JoinColumn(unique = true)
    private League league;

    @OneToOne
    @JoinColumn(unique = true)
    private Team homeTeam;

    @OneToOne
    @JoinColumn(unique = true)
    private Team awayTeam;

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

    public Match timeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
        return this;
    }

    public void setTimeOfMessage(Instant timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public Long getMatchId() {
        return matchId;
    }

    public Match matchId(Long matchId) {
        this.matchId = matchId;
        return this;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Instant getMatchDate() {
        return matchDate;
    }

    public Match matchDate(Instant matchDate) {
        this.matchDate = matchDate;
        return this;
    }

    public void setMatchDate(Instant matchDate) {
        this.matchDate = matchDate;
    }

    public Boolean isCornerSending() {
        return cornerSending;
    }

    public Match cornerSending(Boolean cornerSending) {
        this.cornerSending = cornerSending;
        return this;
    }

    public void setCornerSending(Boolean cornerSending) {
        this.cornerSending = cornerSending;
    }

    public Sport getSport() {
        return sport;
    }

    public Match sport(Sport sport) {
        this.sport = sport;
        return this;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Region getRegion() {
        return region;
    }

    public Match region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public League getLeague() {
        return league;
    }

    public Match league(League league) {
        this.league = league;
        return this;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Match homeTeam(Team team) {
        this.homeTeam = team;
        return this;
    }

    public void setHomeTeam(Team team) {
        this.homeTeam = team;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Match awayTeam(Team team) {
        this.awayTeam = team;
        return this;
    }

    public void setAwayTeam(Team team) {
        this.awayTeam = team;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Match)) {
            return false;
        }
        return id != null && id.equals(((Match) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Match{" +
            "id=" + getId() +
            ", timeOfMessage='" + getTimeOfMessage() + "'" +
            ", matchId=" + getMatchId() +
            ", matchDate='" + getMatchDate() + "'" +
            ", cornerSending='" + isCornerSending() + "'" +
            "}";
    }
}
