package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.enums.RaceFlag;
import com.zyrexinfinity.f1racemanager.enums.RaceStatus;
import com.zyrexinfinity.f1racemanager.enums.Track;
import com.zyrexinfinity.f1racemanager.model.BolidEntity;
import com.zyrexinfinity.f1racemanager.model.ConstructorEntity;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    //Race vars
    private RaceStatus raceStatus = RaceStatus.WAITING;
    private Track track;
    private RaceFlag raceFlag;
    private long sessionTime = 0;
    private Driver driverIterator;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    private List<Driver> drivers = new ArrayList<>();
    private List<ConstructorEntity> teams = new ArrayList<>();
    private List<BolidEntity> bolids = new ArrayList<>();

    public void initializeRace(){
        switch (raceStatus){
            case RaceStatus.WAITING:
                if(fetchData()){
                    //TODO apply settings set by user for the race
                    applySettings();
//                    printService.printSection("Drivers", drivers);
//                    printService.printSection("Teams", teams);
//                    printService.printSection("Bolids", bolids);
                    raceStatus = RaceStatus.READY;
                }else{
                    raceStatus = RaceStatus.FAILED;
                }
            //TODO Move it so the race starts via button click
            case RaceStatus.READY:
                startRace();
            case RaceStatus.STARTED:
                break;
            default:
                printService.printErrorMessage("Couldn't recognise race status");
                break;
        }
    }

    private void startRace(){
        raceStatus = RaceStatus.STARTED;
        raceFlag = RaceFlag.GREEN_FLAG;
        drivers.forEach(driver -> {
            driver.setStatus(DriverStatus.RACING);
        });
        Collections.shuffle(drivers);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("RaceTime: " + sessionTime);
            sessionTime++;
            updateRace();
        }, 0, 1, TimeUnit.SECONDS);


    }

    private void stopRace(){
        raceStatus = RaceStatus.FINISHED;
        scheduler.shutdown();
    }
    //Main logic for race
    private void updateRace(){
        if(raceStatus == RaceStatus.STARTED) {
            for (int i = 0; i < drivers.size(); i++) {
                driverIterator = drivers.get(i);
                printService.printDriverSessionData(driverIterator, i+1);
                long lapTime = driverIterator.projectLapTime(track);
            }
            drivers.sort(Comparator.comparingLong(Driver::getRaceTime));
        }
    }

    private boolean fetchData(){
        try{
            settingsService.getDriverList().forEach((driverEntity) -> {
                drivers.add(new Driver(driverEntity));
            });
            teams = settingsService.getConstructorList();
            bolids = settingsService.getBolidList();

            System.out.println("Successfully fetched Data");
            return true;
        }catch (Exception e){
            printService.printErrorMessage("There was a problem fetching data");
            e.printStackTrace();
            return false;
        }
    }
    private void applySettings(){
        track = Track.Monza;
    }
}
