package com.zyrexinfinity.f1sim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="driver")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;

    private String fullName;
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "teamId")
    @JsonIgnore
    private ConstructorEntity team;

    //affects crash probability
    private int driverAwareness;
    //affects lap times
    private int driverPace;

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
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

    public int getDriverAwareness() {
        return driverAwareness;
    }

    public void setDriverAwareness(int driverAwareness) {
        this.driverAwareness = driverAwareness;
    }

    public int getDriverPace() {
        return driverPace;
    }

    public void setDriverPace(int driverPace) {
        this.driverPace = driverPace;
    }
}
