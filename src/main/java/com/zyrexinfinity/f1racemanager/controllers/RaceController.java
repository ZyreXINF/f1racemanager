package com.zyrexinfinity.f1racemanager.controllers;

import com.zyrexinfinity.f1racemanager.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RaceController {
    @Autowired
    RaceService raceService;

    @RequestMapping("/startRace")
    public String race(){
//        raceService.initializeRace();
        return "race.html";
    }
}
