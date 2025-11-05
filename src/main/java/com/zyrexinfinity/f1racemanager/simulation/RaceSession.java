package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.enums.RaceStatus;
import com.zyrexinfinity.f1racemanager.services.GridService;
import com.zyrexinfinity.f1racemanager.services.PrintService;
import com.zyrexinfinity.f1racemanager.services.RaceCalculationService;

import java.util.List;

public class RaceSession {
    private RaceCalculationService calculationService;
    private GridService gridService;

    private PrintService printService;

    private List<Driver> driversList;

    private RaceStatus raceStatus;
    private RaceSettings settings;
    private CalculationContext calculationContext;
    private int currentLap;

    public RaceSession(RaceSettings settings, List<Driver> grid,
                       RaceCalculationService calc,
                       GridService gridService,
                       PrintService printService) {
        this.settings = settings;
        calculationContext = new CalculationContext(settings);
        this.driversList = grid;
        this.calculationService = calc;
        this.gridService = gridService;
        this.printService = printService;
        currentLap = grid.get(0).getCurrentLap();
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
                        driver.setRaceTime(driver.getRaceTime() + calculatedLapTime);
                        driver.setCurrentLap(driver.getCurrentLap()+1);
                    }else{
                        driver.setStatus(status);
                    }
                }
                int i = driversList.indexOf(driver)+1;
                printService.printDriverSessionData(driver,i);
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

    @Override
    public String toString() {
        return "RaceSession{" +
                "driversList=" + driversList +
                ", raceStatus=" + raceStatus +
                ", settings=" + settings +
                ", currentLap=" + currentLap +
                '}';
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

    public void setDriversList(List<Driver> driversList) {
        this.driversList = driversList;
    }
}
