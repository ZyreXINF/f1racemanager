package com.zyrexinfinity.f1racemanager.controllers;

import com.zyrexinfinity.f1racemanager.services.RaceSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingsController {
    @Autowired
    RaceSettingsService raceSettingsService;
    @RequestMapping("/raceSetup")
    public String startMenu(Model model){
        model.addAttribute("defaults", raceSettingsService.getDefaultSettings());
        return "raceSetup.html";
    }
}
