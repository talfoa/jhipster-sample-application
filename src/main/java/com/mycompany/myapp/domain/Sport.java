package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Sport.
 */
@Entity
@Table(name = "sport")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "sport")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<League> leagues = new HashSet<>();

    @OneToMany(mappedBy = "sport")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Team> sports = new HashSet<>();

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

    public Sport name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public Sport leagues(Set<League> leagues) {
        this.leagues = leagues;
        return this;
    }

    public Sport addLeague(League league) {
        this.leagues.add(league);
        league.setSport(this);
        return this;
    }

    public Sport removeLeague(League league) {
        this.leagues.remove(league);
        league.setSport(null);
        return this;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }

    public Set<Team> getSports() {
        return sports;
    }

    public Sport sports(Set<Team> teams) {
        this.sports = teams;
        return this;
    }

    public Sport addSport(Team team) {
        this.sports.add(team);
        team.setSport(this);
        return this;
    }

    public Sport removeSport(Team team) {
        this.sports.remove(team);
        team.setSport(null);
        return this;
    }

    public void setSports(Set<Team> teams) {
        this.sports = teams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sport)) {
            return false;
        }
        return id != null && id.equals(((Sport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sport{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
