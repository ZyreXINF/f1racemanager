package com.zyrexinfinity.f1racemanager.controllers;

import com.zyrexinfinity.f1racemanager.enums.RaceStatus;
import com.zyrexinfinity.f1racemanager.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RaceController {
    @Autowired
    RaceService raceService;

    private boolean raceInitialized = false;

    @RequestMapping("/race")
    public String race(Model model){
        if(raceService.getRaceStatus() == RaceStatus.WAITING){
            raceInitialized = raceService.initializeRace();
        }
        if(raceInitialized){
            model.addAttribute("drivers", raceService.getDrivers());
        }
        return "race.html";
    }
}
