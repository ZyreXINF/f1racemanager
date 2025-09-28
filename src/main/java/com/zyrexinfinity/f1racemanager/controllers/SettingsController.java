package com.zyrexinfinity.f1racemanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingsController {
    @RequestMapping("/startMenu")
    public String startMenu(){
        return "startMenu.html";
    }
    //Settings Mapping
    @RequestMapping("/raceSettings")
    public String raceSettings(){
        return "raceSettings.html";
    }
    @RequestMapping("/trackSettings")
    public String trackSettings(){
        return "trackSettings.html";
    }
    @RequestMapping("/gridSettings")
    public String gridSettings(){
        return "gridSettings.html";
    }
    @RequestMapping("/teamSettings")
    public String teamSettings(){
        return "teamSettings.html";
    }
    @RequestMapping("/modeSettings")
    public String modeSettings(){
        return "modeSettings.html";
    }
}
