package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.enums.Track;
import com.zyrexinfinity.f1racemanager.model.DriverEntity;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

        this.driverAwareness = Math.round((double) (100-driverBluePrint.getDriverAwareness())/50.0 * 1000.0) / 1000.0;
        this.driverPace = Math.round(((double) driverBluePrint.getDriverPace() - 60) / (100 - 60) * 1000.0) / 1000.0;

        this.bolid = new Bolid(driverBluePrint.getTeam().getBolid());
        this.team = new Team(driverBluePrint.getTeam());

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

    public long projectSectorTime(Track track, byte sectorNumber) {
        long bestSectorTime = track.getSectorTime(sectorNumber) - 1_000;
        long worstSectorTime = track.getSectorTime(sectorNumber) + 1_000;

        double aeroFactor = 1.0 - bolid.getAerodynamicRating();
        //60% Driver, 40% Car
        double performanceFactor = (driverPace * 0.6) + (aeroFactor * 0.4);

        double driverAverageSectorTime = worstSectorTime - (worstSectorTime - bestSectorTime) * performanceFactor;

        double minDeviation = 0.005;  // 0.5%
        double maxDeviation = 0.03;   // 3%
        double relativeDeviation = maxDeviation - (maxDeviation - minDeviation) * performanceFactor;

        double randomFactor = 1.0 + (ThreadLocalRandom.current().nextDouble() * 2 - 1) * relativeDeviation;

        long sectorTime = Math.round(driverAverageSectorTime * randomFactor);

        double unpredictability = 1.0 + ThreadLocalRandom.current().nextDouble(-0.02, 0.02);
        sectorTime = Math.round(sectorTime * unpredictability);

        return sectorTime;
    }

    public DriverStatus checkDNF(){
        if(bolid.checkEngineFailure()){
            status = DriverStatus.ReliabilityDNF;
        }else if(checkCrash()){
            status = DriverStatus.CrashDNF;
        }
        return status;
    }

    private boolean checkCrash() {
        double baseChance = 0.0005; //0.05%
        double maxAdded = 0.001; //+0.1% (max: 0.15%)
        double chance = baseChance + (maxAdded * driverAwareness);
        return random.nextDouble() < chance;
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
