package com.zyrexinfinity.f1racemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="driver")
public class DriverEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;

    private String fullName;
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "teamId")
    @JsonIgnore
    private ConstructorEntity team;

    private short driverRating;
    private short driverExperience;
    private short driverRacecraft;
    private short driverAwareness;
    private short driverPace;

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", driverRating=" + driverRating +
                ", driverExperience=" + driverExperience +
                ", driverRacecraft=" + driverRacecraft +
                ", driverAwareness=" + driverAwareness +
                ", driverPace=" + driverPace +
                ", team=" + team.getTeamId() +
                '}';
    }

    public long getDriverId() {
        return driverId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public ConstructorEntity getTeam() {
        return team;
    }

    public void setTeam(ConstructorEntity team) {
        this.team = team;
    }

    public short getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(short driverRating) {
        this.driverRating = driverRating;
    }

    public short getDriverExperience() {
        return driverExperience;
    }

    public void setDriverExperience(short driverExperience) {
        this.driverExperience = driverExperience;
    }

    public short getDriverRacecraft() {
        return driverRacecraft;
    }

    public void setDriverRacecraft(short driverRacecraft) {
        this.driverRacecraft = driverRacecraft;
    }

    public short getDriverAwareness() {
        return driverAwareness;
    }

    public void setDriverAwareness(short driverAwareness) {
        this.driverAwareness = driverAwareness;
    }

    public short getDriverPace() {
        return driverPace;
    }

    public void setDriverPace(short driverPace) {
        this.driverPace = driverPace;
    }
}
