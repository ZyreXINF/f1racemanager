package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.simulation.*;
import org.springframework.stereotype.Service;

@Service
public class RaceSettingsService {

    public RaceSettings applyModifiers(RaceSettings defaultSettings){
        //TODO set Settings from "Grid Settings" tab
        RaceSettings modifiedSettings = new RaceSettings(defaultSettings);
        return modifiedSettings;
    }
}
