package com.zyrexinfinity.f1racemanager.controllers;

import com.zyrexinfinity.f1racemanager.factory.SessionFactory;
import com.zyrexinfinity.f1racemanager.model.SessionData;
import com.zyrexinfinity.f1racemanager.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
public class RaceRestController {
    @Autowired
    RaceService raceService;
    @Autowired
    SessionFactory SessionFactory;

    //TODO Secure requests with Spring Security Authentification

    @PostMapping("/startRace")
    public void startRace(){
        raceService.startRace();
    }

    @PostMapping("/restartRace")
    public void restartRace(){
        raceService.setSession(null);
        boolean raceInitialized = raceService.initRace();
        if(raceInitialized){
            raceService.startRace();
        }
    }

    @GetMapping("/getSessionData")
    public SessionData getSessionData() {
        if (!Objects.isNull(raceService.getSession())) {
            return SessionFactory.createSessionData(raceService.getSession());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No active race session found"
            );
        }
    }
}