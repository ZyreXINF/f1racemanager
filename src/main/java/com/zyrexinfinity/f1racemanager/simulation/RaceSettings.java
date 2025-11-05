package com.zyrexinfinity.f1racemanager.simulation;
import com.zyrexinfinity.f1racemanager.config.RaceSettingsProperties;
import com.zyrexinfinity.f1racemanager.enums.Track;

public class RaceSettings{
    private Track track;
    private double maxTimeDeviation,
            maxDriverPaceDeviation,
            carPerformanceRatio,
            driverPerformanceRatio,
            driverPaceModifier,
            aerodynamicRatingModifier,
            crashRate, engineFailureRate;

    public RaceSettings(){}

    public RaceSettings(Track track, double maxTimeDeviation, double maxDriverPaceDeviation, double carPerformanceRatio, double driverPerformanceRatio, double driverPaceModifier, double aerodynamicRatingModifier, double crashRate, double engineFailureRate) {
        this.track = track;
        this.maxTimeDeviation = maxTimeDeviation;
        this.maxDriverPaceDeviation = maxDriverPaceDeviation;
        this.carPerformanceRatio = carPerformanceRatio;
        this.driverPerformanceRatio = driverPerformanceRatio;
        this.driverPaceModifier = driverPaceModifier;
        this.aerodynamicRatingModifier = aerodynamicRatingModifier;
        this.crashRate = crashRate;
        this.engineFailureRate = engineFailureRate;
    }

    public RaceSettings(RaceSettings other) {
        this.track = other.getTrack();
        this.maxTimeDeviation = other.getMaxTimeDeviation();
        this.maxDriverPaceDeviation = other.getMaxDriverPaceDeviation();
        this.carPerformanceRatio = other.getCarPerformanceRatio();
        this.driverPerformanceRatio = other.getDriverPerformanceRatio();
        this.driverPaceModifier = other.getDriverPaceModifier();
        this.aerodynamicRatingModifier = other.getAerodynamicRatingModifier();
        this.crashRate = other.getCrashRate();
        this.engineFailureRate = other.getEngineFailureRate();
    }

    public RaceSettings(RaceSettingsProperties defaults) {
        this.track = defaults.getTrack();
        this.maxTimeDeviation = defaults.getMaxTimeDeviation();
        this.maxDriverPaceDeviation = defaults.getMaxDriverPaceDeviation();
        this.carPerformanceRatio = defaults.getCarPerformanceRatio();
        this.driverPerformanceRatio = defaults.getDriverPerformanceRatio();
        this.driverPaceModifier = defaults.getDriverPaceModifier();
        this.aerodynamicRatingModifier = defaults.getAerodynamicRatingModifier();
        this.crashRate = defaults.getCrashRate();
        this.engineFailureRate = defaults.getEngineFailureRate();
    }

    @Override
    public String toString() {
        return "RaceSettings{" +
                "track=" + track +
                ", maxTimeDeviation=" + maxTimeDeviation +
                ", maxDriverPaceDeviation=" + maxDriverPaceDeviation +
                ", carPerformanceRatio=" + carPerformanceRatio +
                ", driverPerformanceRatio=" + driverPerformanceRatio +
                ", driverPaceModifier=" + driverPaceModifier +
                ", aerodynamicRatingModifier=" + aerodynamicRatingModifier +
                ", crashRate=" + crashRate +
                ", engineFailureRate=" + engineFailureRate +
                '}';
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public double getMaxDriverPaceDeviation() {
        return maxDriverPaceDeviation;
    }

    public void setMaxDriverPaceDeviation(double maxDriverPaceDeviation) {
        this.maxDriverPaceDeviation = maxDriverPaceDeviation;
    }

    public double getCarPerformanceRatio() {
        return carPerformanceRatio;
    }

    public void setCarPerformanceRatio(double carPerformanceRatio) {
        this.carPerformanceRatio = carPerformanceRatio;
    }

    public double getDriverPerformanceRatio() {
        return driverPerformanceRatio;
    }

    public void setDriverPerformanceRatio(double driverPerformanceRatio) {
        this.driverPerformanceRatio = driverPerformanceRatio;
    }

    public double getDriverPaceModifier() {
        return driverPaceModifier;
    }

    public void setDriverPaceModifier(double driverPaceModifier) {
        this.driverPaceModifier = driverPaceModifier;
    }

    public double getAerodynamicRatingModifier() {
        return aerodynamicRatingModifier;
    }

    public void setAerodynamicRatingModifier(double aerodynamicRatingModifier) {
        this.aerodynamicRatingModifier = aerodynamicRatingModifier;
    }

    public double getCrashRate() {
        return crashRate;
    }

    public void setCrashRate(double crashRate) {
        this.crashRate = crashRate;
    }

    public double getEngineFailureRate() {
        return engineFailureRate;
    }

    public void setEngineFailureRate(double engineFailureRate) {
        this.engineFailureRate = engineFailureRate;
    }

    public double getMaxTimeDeviation() {
        return maxTimeDeviation;
    }

    public void setMaxTimeDeviation(double maxTimeDeviation) {
        this.maxTimeDeviation = maxTimeDeviation;
    }
}
