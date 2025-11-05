package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.model.DriverEntity;

import java.util.Random;

public class Driver {
    private final String fullName;
    private final String nationality;
    private double driverAwareness;
    private double driverPace;

    private Bolid bolid;
    private Team team;
    private static final Random random = new Random();

    private long raceTime;
    private int currentLap;
    private DriverStatus status = DriverStatus.DNS;

    public Driver(DriverEntity driverBluePrint) {
        this.fullName = driverBluePrint.getFullName();
        this.nationality = driverBluePrint.getNationality();

        this.driverAwareness = driverBluePrint.getDriverAwareness();
        this.driverPace = driverBluePrint.getDriverPace();

        this.bolid = new Bolid(driverBluePrint.getTeam().getBolid());
        this.team = new Team(driverBluePrint.getTeam());

        this.currentLap = 0;
        this.raceTime = 0;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", driverAwareness=" + driverAwareness +
                ", driverPace=" + driverPace +
                ", bolid=" + bolid +
                ", raceTime=" + raceTime +
                ", currentLap=" + currentLap +
                ", status=" + status +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public double getDriverAwareness() {
        return driverAwareness;
    }

    public void setDriverAwareness(double driverAwareness) {
        this.driverAwareness = Math.round((100-driverAwareness) /50.0 * 1000.0) / 1000.0;
    }

    public double getDriverPace() {
        return driverPace;
    }

    public void setDriverPace(double driverPace) {
        this.driverPace = Math.round((driverPace - 60) / (100 - 60) * 1000.0) / 1000.0;
    }

    public Bolid getBolid() {
        return bolid;
    }

    public void setBolid(Bolid bolid) {
        this.bolid = bolid;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public long getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(long raceTime) {
        this.raceTime = raceTime;
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

}
