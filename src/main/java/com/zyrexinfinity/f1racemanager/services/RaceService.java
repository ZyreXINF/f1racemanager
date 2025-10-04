package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.enums.RaceFlag;
import com.zyrexinfinity.f1racemanager.enums.RaceStatus;
import com.zyrexinfinity.f1racemanager.enums.Track;
import com.zyrexinfinity.f1racemanager.model.BolidEntity;
import com.zyrexinfinity.f1racemanager.model.ConstructorEntity;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RaceService {
    //Services
    @Autowired
    RaceSettingsService settingsService;
    @Autowired
    PrintService printService;

    private RaceStatus raceStatus = RaceStatus.WAITING;
    private Track track;
    private RaceFlag raceFlag;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Duration sessionTime = Duration.ZERO;


    private List<Driver> drivers;
    private List<ConstructorEntity> teams;
    private List<BolidEntity> bolids;

    public void initializeRace(){
        switch (raceStatus){
            case RaceStatus.WAITING:
                if(fetchData()){
                    //TODO apply settings set by user for the race
                    applySettings();
//                    printService.printSection("Drivers", drivers);
//                    printService.printSection("Teams", teams);
//                    printService.printSection("Bolids", bolids);
                    System.out.println("Successfully fetched Data");
                    raceStatus = RaceStatus.READY;
                }else{
                    raceStatus = RaceStatus.FAILED;
                }

            //TODO Move it so the race starts via button click
            case RaceStatus.READY:
                startRace();
            default:
                printService.printErrorMessage("Couldn't recognise race status");
                break;
        }
    }

    private void startRace(){
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("RaceTime: " + sessionTime.toSeconds());
            sessionTime.plusSeconds(1);
            updateRace();
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void stopRace(){
        raceStatus = RaceStatus.FINISHED;
        scheduler.shutdown();
    }
    //Main logic for race
    private void updateRace(){
        raceStatus = RaceStatus.STARTED;
        while (raceStatus == RaceStatus.STARTED){
            drivers.forEach(driver -> {
                driver.projectLapTime(track);
            });
        }
    }

    private boolean fetchData(){
        try{
            settingsService.getDriverList().forEach((driverEntitiy) -> {
                drivers.add(new Driver(driverEntitiy));
            });
            teams = settingsService.getConstructorList();
            bolids = settingsService.getBolidList();

            System.out.println("Successfully fetched Data");
            return true;
        }catch (Exception e){
            printService.printErrorMessage("There was a problem fetching data");
            return false;
        }
    }
    private void applySettings(){
        drivers.forEach((_)->{

        });
        track = Track.Monza;
        raceFlag = RaceFlag.GREEN_FLAG;
    }
}
