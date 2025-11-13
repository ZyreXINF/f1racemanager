package com.zyrexinfinity.f1sim.model;

import com.zyrexinfinity.f1sim.enums.RaceStatus;
import com.zyrexinfinity.f1sim.simulation.Driver;
import com.zyrexinfinity.f1sim.simulation.RaceSession;

import java.util.ArrayList;
import java.util.List;

public class SessionData {
    private List<Driver> driversList;
    private RaceStatus raceStatus;
    private int currentLap;
    private int lapAmount;
    public SessionData(RaceSession session){
        this.raceStatus = session.getRaceStatus();
        this.driversList = new ArrayList<>(session.getDriversList());
        this.currentLap = session.getCurrentLap();
        this.lapAmount = session.getSettings().getTrack().getLapsNumber();
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "driversList=" + driversList +
                ", raceStatus=" + raceStatus +
                ", currentLap=" + currentLap +
                ", lapAmount=" + lapAmount +
                '}';
    }

    public List<Driver> getDriversList() {
        return driversList;
    }

    public RaceStatus getRaceStatus() {
        return raceStatus;
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public int getLapAmount() {
        return lapAmount;
    }
}
