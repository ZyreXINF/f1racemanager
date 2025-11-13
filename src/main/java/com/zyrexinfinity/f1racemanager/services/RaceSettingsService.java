package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.factory.RaceSettingsFactory;
import com.zyrexinfinity.f1racemanager.simulation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceSettingsService {
    @Autowired
    RaceSettingsFactory raceSettingsFactory;
    RaceSettings settings;

    public RaceSettings getDefaultSettings(){
        return raceSettingsFactory.createDefaultSettings();
    }
    public RaceSettings getSettings() {
        return settings;
    }
    public void setSettings(RaceSettings settings) {
        this.settings = settings;
    }
}
