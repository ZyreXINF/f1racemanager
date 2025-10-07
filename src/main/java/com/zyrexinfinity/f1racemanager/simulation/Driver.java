package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.enums.Track;
import com.zyrexinfinity.f1racemanager.model.DriverEntity;

import java.util.concurrent.ThreadLocalRandom;

public class Driver {
    private final String fullName;
    private final String nationality;
    private final double driverAwareness;
    private final double driverPace;

    private long raceTime;
    private int currentLap;
    private DriverStatus status = DriverStatus.DNS;

    public Driver(DriverEntity driverBluePrint) {
        this.fullName = driverBluePrint.getFullName();
        this.nationality = driverBluePrint.getNationality();

        this.driverAwareness = (double) driverBluePrint.getDriverAwareness()/100;
        this.driverPace = Math.round(((double) driverBluePrint.getDriverPace() - 65) / (100 - 65) * 1000.0) / 1000.0;

        this.currentLap = 0;
        this.raceTime = 0;
    }

    public long projectLapTime(Track track){
        long lapTime = 0;
        for (byte i = 1; i <= 3; i++) {
            lapTime += projectSectorTime(track, i);
        }
        raceTime += lapTime;
        currentLap++;
        return lapTime;
    }

    public long projectSectorTime(Track track, byte sectorNumber){
        long bestSectorTime = track.getSectorTime(sectorNumber) - 1_000;
        long worstSectorTime = track.getSectorTime(sectorNumber) + 1_000;

        double driverAverageSectorTime = worstSectorTime - (worstSectorTime - bestSectorTime) * driverPace;

        double minDeviation = 0.005;  // 0.5%
        double maxDeviation = 0.03;   // 3%
        double relativeDeviation = maxDeviation - (maxDeviation - minDeviation) * driverPace;

        double randomFactor = 1.0 + (ThreadLocalRandom.current().nextDouble() * 2 - 1) * relativeDeviation;

        long sectorTime = Math.round(driverAverageSectorTime * randomFactor);
        return sectorTime;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", driverAwareness=" + driverAwareness +
                ", driverPace=" + driverPace +
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

    public double getDriverPace() {
        return driverPace;
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
