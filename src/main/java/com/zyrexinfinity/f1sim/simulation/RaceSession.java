package com.zyrexinfinity.f1sim.simulation;

import com.zyrexinfinity.f1sim.enums.DriverStatus;
import com.zyrexinfinity.f1sim.enums.RaceStatus;
import com.zyrexinfinity.f1sim.services.GridService;
import com.zyrexinfinity.f1sim.services.RaceCalculationService;

import java.util.List;

public class RaceSession {
    //Services
    private RaceCalculationService calculationService;
    private GridService gridService;

    //Settings/Calculation-Related Variables
    private RaceSettings settings;
    private CalculationContext calculationContext;

    //Simulation-Related Variables
    private RaceStatus raceStatus;
    private int currentLap;
    private long fastestLapTime;
    private List<Driver> driversList;

    public RaceSession(RaceSettings settings, List<Driver> grid,
                       RaceCalculationService calc,
                       GridService gridService) {
        this.settings = settings;
        this.calculationContext = new CalculationContext(settings);
        this.driversList = grid;
        this.calculationService = calc;
        this.gridService = gridService;
        this.currentLap = grid.get(0).getCurrentLap();
        this.fastestLapTime = 0;
    }

    public boolean update(){
        if(raceStatus == RaceStatus.RACING){
            driversList.forEach(driver -> {
                if(driver.getStatus() == DriverStatus.RACING){
                    calculationContext.setDriver(driver);

                    //Calculation of DNF
                    DriverStatus status = calculationService.calculateStatus(calculationContext);

                    if(status == DriverStatus.RACING){
                        //Calculation of lap time
                        long calculatedLapTime = calculationService.calculateLapTime(calculationContext);
                        long driversFastestLap = driver.getFastestLap();

                        //Personal Best Laptime
                        if (driversFastestLap <= 0 || calculatedLapTime < driversFastestLap) {
                            driver.setFastestLap(calculatedLapTime);
                        }

                        //Overall Best Laptime
                        if (fastestLapTime <= 0 || calculatedLapTime < fastestLapTime) {
                            fastestLapTime = calculatedLapTime;
                            driversList.forEach(driver1 -> {driver1.setSetFastestLap(false);});
                            driver.setSetFastestLap(true);
                        }

                        driver.setRaceTime(driver.getRaceTime() + calculatedLapTime);
                        driver.setCurrentLap(driver.getCurrentLap()+1);
                    }else{
                        driver.setStatus(status);
                    }
                }
            });

            currentLap++;

            if(currentLap == settings.getTrack().getLapsNumber()){
                driversList.forEach(driver -> {
                    if(driver.getStatus() == DriverStatus.RACING){
                        driver.setStatus(DriverStatus.Finished);
                    }
                });
                stop();
            }

            driversList = gridService.sortPositions(driversList);
            return true;
        }
        return false;
    }

    private void stop(){
        raceStatus = RaceStatus.FINISHED;
    }

    public RaceStatus getRaceStatus() {
        return raceStatus;
    }

    public void setRaceStatus(RaceStatus raceStatus) {
        this.raceStatus = raceStatus;
    }

    public List<Driver> getDriversList() {
        return driversList;
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public RaceSettings getSettings() {
        return settings;
    }
}
