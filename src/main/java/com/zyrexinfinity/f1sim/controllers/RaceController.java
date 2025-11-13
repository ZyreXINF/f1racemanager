package com.zyrexinfinity.f1sim.controllers;

import com.zyrexinfinity.f1sim.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RaceController {
    @Autowired
    RaceService raceService;

    @RequestMapping("/race")
    public String race(Model model){
        boolean raceInitialized = raceService.initRace();
        model.addAttribute("drivers", raceService.getSession().getDriversList());
        return "race.html";
    }
}
