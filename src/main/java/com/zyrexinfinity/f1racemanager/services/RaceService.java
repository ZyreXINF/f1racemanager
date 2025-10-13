package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.enums.*;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class RaceService {
    //Services
    @Autowired
    RaceSettingsService settingsService;
    @Autowired
    PrintService printService;

    //Race vars
    private RaceStatus raceStatus = RaceStatus.WAITING;
    private Track track;
    private RaceFlag raceFlag;
    private long sessionTime = 0;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private List<Driver> drivers;

    public boolean initializeRace() {
        if (raceStatus == RaceStatus.WAITING) {
            if (fetchData()) {
                //TODO apply settings set by user for the race
                applySettings();
                raceStatus = RaceStatus.READY;
                Collections.shuffle(drivers);
                return true;
            } else {
                raceStatus = RaceStatus.FAILED;
                return false;
            }
        }else{
            printService.printColoredMessage("Invalid raceStatus",MessageColor.RED);
            System.out.println("RaceStatus: " + raceStatus);
            return false;
        }
    }


    public void startRace(){
        if(raceStatus == RaceStatus.READY){
            raceStatus = RaceStatus.STARTED;
            raceFlag = RaceFlag.GREEN_FLAG;
            drivers.forEach(driver -> {
                driver.setStatus(DriverStatus.RACING);
            });
            setStartingPosition();
            scheduler.scheduleAtFixedRate(() -> {
                System.out.println("RaceTime: " + sessionTime);
                sessionTime++;
                updateRace();
            }, 0, 1, TimeUnit.SECONDS);
        }else{
            printService.printColoredMessage("The race is not ready or not properly set", MessageColor.RED);
        }
    }

    private void stopRace(){
        scheduler.shutdown();
        raceStatus = RaceStatus.FINISHED;
        System.out.println("\nRACE FINISHED\n");
        printService.printRaceResult(drivers);
    }

    //Main logic for race
    private void updateRace(){
        if(raceStatus == RaceStatus.STARTED) {
            for (int i = 0; i < drivers.size(); i++) {
                Driver driverIterator = drivers.get(i);
                if(driverIterator.getStatus() == DriverStatus.RACING){
                    driverIterator.checkDNF();
                    long lapTime = driverIterator.projectLapTime(track);
                }
                printService.printDriverSessionData(driverIterator, i+1);
            }
            if(drivers.get(0).getCurrentLap() == track.getLapsNumber()){
                drivers.forEach(driver -> {
                    if(driver.getStatus() == DriverStatus.RACING){
                        driver.setStatus(DriverStatus.Finished);
                    }
                });
                stopRace();
            }
            sortPositions();
        }
    }

    private void sortPositions(){
        drivers.sort((d1, d2) -> {
            boolean d1Dnf = d1.getStatus() == DriverStatus.CrashDNF || d1.getStatus() == DriverStatus.ReliabilityDNF || d1.getStatus() == DriverStatus.DNS;
            boolean d2Dnf = d2.getStatus() == DriverStatus.CrashDNF || d2.getStatus() == DriverStatus.ReliabilityDNF || d2.getStatus() == DriverStatus.DNS;

            if (!d1Dnf && !d2Dnf) {
                return Long.compare(d1.getRaceTime(), d2.getRaceTime());
            }

            if (d1Dnf && d2Dnf) {
                return Long.compare(d2.getRaceTime(), d1.getRaceTime());
            }

            return d1Dnf ? 1 : -1;
        });
    }

    private void setStartingPosition(){
        for (int i = drivers.size()-1; i >= 0; i--) {
            System.out.println(i);
            drivers.get(i).setRaceTime(i* 200L);
        }
    }

    private boolean fetchData(){
        try{
            drivers = settingsService.getDriverList();

            System.out.println("Successfully fetched Data");
            return true;
        }catch (Exception e){
            printService.printColoredMessage("There was a problem fetching data", MessageColor.RED);
            return false;
        }
    }

    private void applySettings(){
        track = Track.Monza;
        drivers.forEach(driver -> {
            driver.setDriverPace(driver.getDriverPace() * (1.0 + ThreadLocalRandom.current().nextDouble(-0.01, 0.01)));
        });
    }

    public List<Driver> getDrivers() {
        return drivers;
    }
    public RaceStatus getRaceStatus() {
        return raceStatus;
    }
}
