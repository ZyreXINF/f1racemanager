package com.zyrexinfinity.f1racemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="constructor")
public class ConstructorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;
    private String teamName;

    private int crewRating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bolidId")
    @JsonIgnore
    private BolidEntity bolid;

    @Override
    public String toString() {
        return "Constructor{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", crewRating=" + crewRating +
                ", bolid=" + bolid.getBolidId() +
                '}';
    }

    public long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getCrewRating() {
        return crewRating;
    }

    public void setCrewRating(int crewRating) {
        this.crewRating = crewRating;
    }

    public BolidEntity getBolid() {
        return bolid;
    }

    public void setBolid(BolidEntity bolid) {
        this.bolid = bolid;
    }
}