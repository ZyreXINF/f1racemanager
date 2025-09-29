package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.*;

@Entity
public class Constructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;
    private String teamName;

    private short crewRating; //affect overall pitstop time rating

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bolidId")
    private Bolid bolid;

    public long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public short getCrewRating() {
        return crewRating;
    }

    public void setCrewRating(short crewRating) {
        this.crewRating = crewRating;
    }

    public Bolid getBolid() {
        return bolid;
    }

    public void setBolid(Bolid bolid) {
        this.bolid = bolid;
    }
}