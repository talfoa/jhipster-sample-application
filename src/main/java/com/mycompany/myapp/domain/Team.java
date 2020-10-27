package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Team.
 */
@Entity
@Table(name = "team")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "flag")
    private String flag;

    @OneToMany(mappedBy = "team")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Player> players = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "team_league",
               joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "league_id", referencedColumnName = "id"))
    private Set<League> leagues = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "sports", allowSetters = true)
    private Sport sport;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Team name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public Team color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFlag() {
        return flag;
    }

    public Team flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Team players(Set<Player> players) {
        this.players = players;
        return this;
    }

    public Team addPlayer(Player player) {
        this.players.add(player);
        player.setTeam(this);
        return this;
    }

    public Team removePlayer(Player player) {
        this.players.remove(player);
        player.setTeam(null);
        return this;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public Team leagues(Set<League> leagues) {
        this.leagues = leagues;
        return this;
    }

    public Team addLeague(League league) {
        this.leagues.add(league);
        league.getTeams().add(this);
        return this;
    }

    public Team removeLeague(League league) {
        this.leagues.remove(league);
        league.getTeams().remove(this);
        return this;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }

    public Sport getSport() {
        return sport;
    }

    public Team sport(Sport sport) {
        this.sport = sport;
        return this;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Team)) {
            return false;
        }
        return id != null && id.equals(((Team) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Team{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", color='" + getColor() + "'" +
            ", flag='" + getFlag() + "'" +
            "}";
    }
}
