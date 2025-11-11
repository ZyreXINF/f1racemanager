package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.model.DriverEntity;

import java.util.Random;

public class Driver {
    private String fullName;
    private String nationality;
    private double driverAwareness;
    private double driverPace;

    private Bolid bolid;
    private Team team;
    private static final Random random = new Random();

    private long raceTime;
    private int currentLap;
    private long fastestLap;
    private DriverStatus status = DriverStatus.DNS;
    private boolean setFastestLap;

    public Driver(DriverEntity driverBluePrint) {
        this.fullName = driverBluePrint.getFullName();
        this.nationality = driverBluePrint.getNationality();

        this.driverAwareness = driverBluePrint.getDriverAwareness();
        this.driverPace = driverBluePrint.getDriverPace();

        this.bolid = new Bolid(driverBluePrint.getTeam().getBolid());
        this.team = new Team(driverBluePrint.getTeam());

        this.currentLap = 0;
        this.raceTime = 0;
        this.fastestLap = 0;

        this.setFastestLap = false;
    }
    public Driver(){}

    public Driver clone(){
        Driver cloned = new Driver();
        cloned.setFullName(this.fullName);
        cloned.setNationality(this.nationality);
        cloned.setDriverAwareness(this.driverAwareness);
        cloned.setDriverPace(this.driverPace);
        cloned.setBolid(this.bolid);
        cloned.setTeam(this.team);
        cloned.setCurrentLap(this.getCurrentLap());
        cloned.setRaceTime(this.getRaceTime());
        cloned.setFastestLap(this.fastestLap);
        return cloned;
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
                ", fastestLap=" + fastestLap +
                '}';
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

    public double getDriverAwareness() {
        return driverAwareness;
    }

    public void setDriverAwareness(double driverAwareness) {
        this.driverAwareness = driverAwareness;
    }

    public double getDriverPace() {
        return driverPace;
    }

    public void setDriverPace(double driverPace) {
        this.driverPace = driverPace;
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

    public long getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(long fastestLap) {
        this.fastestLap = fastestLap;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public boolean isSetFastestLap() {
        return setFastestLap;
    }

    public void setSetFastestLap(boolean setFastestLap) {
        this.setFastestLap = setFastestLap;
    }
}
