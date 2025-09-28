package com.zyrexinfinity.f1racemanager.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RaceController {
    @Value("${app.version}")
    private String appVersion;

    @RequestMapping("/startRace")
    public String race(){
        System.out.println(appVersion);
        return "race.html";
    }
}
