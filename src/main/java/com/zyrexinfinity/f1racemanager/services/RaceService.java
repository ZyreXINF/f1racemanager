package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.model.Bolid;
import com.zyrexinfinity.f1racemanager.model.Constructor;
import com.zyrexinfinity.f1racemanager.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {
    @Autowired
    RaceSettingsService settingsService;
    @Autowired
    PrintService printService;

    @Value("${app.version}")
    private String appVersion;

    private String raceStatus = "Waiting"; //Waiting, Started, Finished
    private List<Driver> drivers;
    List<Constructor> teams;
    List<Bolid> bolids;

    public void raceSimulation(){
        switch (raceStatus){
            case "Waiting":
                fetchSettings();
//                printService.printSection("Drivers", drivers);
//                printService.printSection("Teams", teams);
//                printService.printSection("Bolids", bolids);
                System.out.println("Successfully fetched Data");
                break;
        }
    }
    private boolean fetchSettings(){
        try{
            drivers = settingsService.getDriverSettings();
            teams = settingsService.getConstructorSettings();
            bolids = settingsService.getBolidSettings();
            System.out.println("Successfully fetched Data");
            return true;
        }catch (Exception e){
            System.out.println("\u001B[31mThere was a problem fetching data \u001B[0m");
            return false;
        }
    }
}
