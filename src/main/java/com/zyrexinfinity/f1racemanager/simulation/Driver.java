package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.enums.Track;
import com.zyrexinfinity.f1racemanager.model.DriverEntitiy;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {
    private final String fullName;
    private final String nationality;
    private final double driverRating;
    private final double driverExperience;
    private final double driverRacecraft;
    private final double driverAwareness;
    private final double driverPace;

    private Duration raceTime;
    private int currentLap;
    private DriverStatus status = DriverStatus.DNS;
    private double lapProgression;

    public Driver(DriverEntitiy driverBluePrint) {
        this.fullName = driverBluePrint.getFullName();
        this.nationality = driverBluePrint.getNationality();
//        this.driverRating = (double) driverBluePrint.getDriverRating() /100;
        this.driverRating = (driverBluePrint.getDriverRating() - 60) / (100.0 - 60);
        this.driverExperience = (double) driverBluePrint.getDriverExperience() /100;
        this.driverRacecraft = (double) driverBluePrint.getDriverRacecraft() /100;
        this.driverAwareness = (double) driverBluePrint.getDriverAwareness()/100;
        this.driverPace = 1.94 - ((double) driverBluePrint.getDriverPace() /100);

        this.currentLap = 1;
        this.raceTime = Duration.ZERO;
        this.lapProgression = 0;
    }

    public void projectLapTime(Track track){
        double trackLapTime = track.getLapTime();

        double minLapTime = trackLapTime - 2.0; // best possible lap
        double maxLapTime = trackLapTime + 3.0; // worst possible lap
        double centerLap = maxLapTime - (maxLapTime - minLapTime) * driverRating;

        double maxDeviation = 0.5 + (1.0 - driverRating) * 1.5;

        double lapTimeSeconds = centerLap + (ThreadLocalRandom.current().nextDouble() * 2 - 1) * maxDeviation;
        lapTimeSeconds = Math.round(lapTimeSeconds * 1000.0) / 1000.0;

        Duration lapTime = Duration.ofNanos(Math.round(lapTimeSeconds * 1_000_000_000));
        raceTime.plusNanos(lapTime.toNanos());
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public double getDriverRating() {
        return driverRating;
    }

    public double getDriverExperience() {
        return driverExperience;
    }

    public double getDriverRacecraft() {
        return driverRacecraft;
    }

    public double getDriverAwareness() {
        return driverAwareness;
    }

    public double getDriverPace() {
        return driverPace;
    }

    public Duration getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(Duration raceTime) {
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

    public double getLapProgression() {
        return lapProgression;
    }

    public void setLapProgression(double lapProgression) {
        this.lapProgression = lapProgression;
    }
}
