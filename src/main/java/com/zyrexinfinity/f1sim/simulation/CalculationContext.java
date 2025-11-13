package com.zyrexinfinity.f1sim.simulation;

public class CalculationContext{
    private RaceSettings settings;
    private Driver driver;

    public CalculationContext(RaceSettings settings) {
        this.settings = settings;
    }

    public RaceSettings getSettings() {
        return settings;
    }

    public void setSettings(RaceSettings settings) {
        this.settings = settings;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
