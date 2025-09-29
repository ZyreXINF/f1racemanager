package com.zyrexinfinity.f1racemanager.controllers;

import com.zyrexinfinity.f1racemanager.model.BolidRepo;
import com.zyrexinfinity.f1racemanager.model.ConstructorRepo;
import com.zyrexinfinity.f1racemanager.model.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RaceController {
    @Value("${app.version}")
    private String appVersion;

    @RequestMapping("/startRace")
    public String race(){
        return "race.html";
    }
}
