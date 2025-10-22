package com.zyrexinfinity.f1racemanager.controllers;

import com.zyrexinfinity.f1racemanager.enums.RaceStatus;
import com.zyrexinfinity.f1racemanager.services.RaceService;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RaceRestController {
    @Autowired
    RaceService raceService;

    //TODO Secure requests with Spring Security Authentification

    @PostMapping("/startRace")
    public void startRace(){
        raceService.startRace();
    }

    @PostMapping("/restartRace")
    public void restartRace(){
        raceService.restartRace();
    }

    @GetMapping("/getDriverData")
    public List<Driver> getDriverPositions(){
        System.out.println("Requested Drivers Data");
        return raceService.getDrivers();
    }
    @GetMapping("/getInitialGrid")
    public List<Driver> getInitialGrid(){
        System.out.println("Requested Initial Grid Data Data");
        return raceService.getGrid();
    }

    @GetMapping("/getRaceStatus")
    public RaceStatus getRaceStatus(){
        System.out.println("Requested Race Status Data");
        return raceService.getRaceStatus();
    }

    @GetMapping("/test")
    public boolean testRequest(){
        return true;
    }
}
