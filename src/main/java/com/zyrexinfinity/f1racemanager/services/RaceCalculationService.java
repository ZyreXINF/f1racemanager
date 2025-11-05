package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.simulation.CalculationContext;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import com.zyrexinfinity.f1racemanager.simulation.RaceSettings;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RaceCalculationService {
    public long calculateLapTime(CalculationContext calculationContext){
        long lapTime = 0L;
        for(int i = 1; i <= 3; i++){
            lapTime += calculateSectorTime(i, calculationContext);
        }
        return lapTime;
    }

    public DriverStatus calculateStatus(CalculationContext calculationContext){
        if(calculateEngineFailure(calculationContext)){
            return DriverStatus.ReliabilityDNF;
        }else if(calculateCrash(calculationContext)){
            return DriverStatus.CrashDNF;
        }
        return DriverStatus.RACING;
    }

    private boolean calculateCrash(CalculationContext calculationContext){
        RaceSettings settings = calculationContext.getSettings();
        Driver driver = calculationContext.getDriver();
        double awareness = driver.getDriverAwareness()/100 * settings.getCrashRate();

        double baseChance = 0.0005;
        double maxAdded = 0.0015;
        double chance = baseChance + (maxAdded * awareness);
        return ThreadLocalRandom.current().nextDouble(0.0, 1.0) <= chance;
    }

    private boolean calculateEngineFailure(CalculationContext calculationContext){
        RaceSettings settings = calculationContext.getSettings();
        Driver driver = calculationContext.getDriver();
        double reliability = driver.getBolid().getReliability()/100 * settings.getEngineFailureRate();

        double baseChance = 0.0005;
        double maxAdded = 0.0015;
        double chance = baseChance + (maxAdded * reliability);
        return ThreadLocalRandom.current().nextDouble(0.0, 1.0) <= chance;
    }

    //TODO Increase max deviation
    private long calculateSectorTime(int sectorNumber, CalculationContext calculationContext){
        RaceSettings settings = calculationContext.getSettings();
        Driver driver = calculationContext.getDriver();

        double driverPace = (driver.getDriverPace()/100) *
                settings.getDriverPaceModifier();
        double maxDriverPaceDeviation = settings.getMaxDriverPaceDeviation();
        // DriverPace + Random * MaxRandomDeviation
        double modifiedDriverPace = clamp(driverPace +
                (ThreadLocalRandom.current().nextDouble(-1.0, 1.0) *
                        maxDriverPaceDeviation),
                0,1);

        double driverPerformanceRatio = settings.getDriverPerformanceRatio();
        double carPerformanceRatio = settings.getCarPerformanceRatio();
        double bolidAerodynamicRating = (driver.getBolid().getAerodynamicRating()/100) *
                settings.getAerodynamicRatingModifier();
        // driverPerformanceRatio * (1-modifiedDriverPace) + carPerformanceRatio * (1 - aerodynamicRating)
        double score = driverPerformanceRatio * (1 - modifiedDriverPace) +
                carPerformanceRatio * (1 - bolidAerodynamicRating);

        double maxTimeDeviation = settings.getMaxTimeDeviation();
        //score * maxTimeDeviation
        double delta = clamp(score * maxTimeDeviation, 0, maxTimeDeviation);

        long baseSectorTime = settings.getTrack().getSectorTime(sectorNumber);
        //baseTime * (1 + delta)
        double projectedSectorTime = baseSectorTime * (1 + delta);
        projectedSectorTime = Math.max(projectedSectorTime, baseSectorTime);

        return Math.round(projectedSectorTime);
    }

    private double clamp(double value, double minRangeBorder, double maxRangeBorder){
        if(value < minRangeBorder){
            return minRangeBorder;
        }else if(value > maxRangeBorder){
            return maxRangeBorder;
        }return value;
    }
}
