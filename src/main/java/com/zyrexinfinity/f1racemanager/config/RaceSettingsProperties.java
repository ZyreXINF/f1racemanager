package com.zyrexinfinity.f1racemanager.config;

import com.zyrexinfinity.f1racemanager.enums.Track;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:race.properties")
@Component
@ConfigurationProperties(prefix = "race.default")
public class RaceSettingsProperties {

    private Track track;
    private double maxTimeDeviation;
    private double maxDriverPaceDeviation;
    private double carPerformanceRatio;
    private double driverPerformanceRatio;
    private double driverPaceModifier;
    private double aerodynamicRatingModifier;
    private double crashRate;
    private double engineFailureRate;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public double getMaxTimeDeviation() {
        return maxTimeDeviation;
    }

    public void setMaxTimeDeviation(double maxTimeDeviation) {
        this.maxTimeDeviation = maxTimeDeviation;
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
}
