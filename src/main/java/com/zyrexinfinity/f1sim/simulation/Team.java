package com.zyrexinfinity.f1sim.simulation;

import com.zyrexinfinity.f1sim.model.ConstructorEntity;

public class Team {
    private String teamName;
    private int crewRating;

    public Team(ConstructorEntity teamBluePrint){
        this.teamName = teamBluePrint.getTeamName();
        this.crewRating = teamBluePrint.getCrewRating();
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
}
