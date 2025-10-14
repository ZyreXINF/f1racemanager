package com.zyrexinfinity.f1racemanager.controllers;

import com.zyrexinfinity.f1racemanager.services.RaceService;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RaceRestController {
    @Autowired
    RaceService raceService;

    @GetMapping("/getDriverPositions")
    public List<Driver> getDriverPositions(){
        System.out.println();
        return raceService.getDrivers();
    }

    @GetMapping("/test")
    public boolean testRequest(){
        return true;
    }
}
