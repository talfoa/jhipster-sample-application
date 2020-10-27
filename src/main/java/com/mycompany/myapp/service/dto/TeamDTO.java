package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Team} entity.
 */
public class TeamDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    private String color;

    private String flag;

    private Set<LeagueDTO> leagues = new HashSet<>();

    private Long sportId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Set<LeagueDTO> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<LeagueDTO> leagues) {
        this.leagues = leagues;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeamDTO)) {
            return false;
        }

        return id != null && id.equals(((TeamDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TeamDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", color='" + getColor() + "'" +
            ", flag='" + getFlag() + "'" +
            ", leagues='" + getLeagues() + "'" +
            ", sportId=" + getSportId() +
            "}";
    }
}
