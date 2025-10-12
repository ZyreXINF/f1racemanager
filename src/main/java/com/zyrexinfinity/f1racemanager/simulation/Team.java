package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.model.ConstructorEntity;

public class Team {
    private String teamName;
    private int crewRating;

    public Team(ConstructorEntity ce){
        this.teamName = ce.getTeamName();
        this.crewRating = ce.getCrewRating();
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
