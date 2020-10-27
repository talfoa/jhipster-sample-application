package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.League} entity.
 */
@ApiModel(description = "not an ignored comment")
public class LeagueDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;


    private Long sportId;

    private Long regionId;
    
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

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeagueDTO)) {
            return false;
        }

        return id != null && id.equals(((LeagueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeagueDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sportId=" + getSportId() +
            ", regionId=" + getRegionId() +
            "}";
    }
}
