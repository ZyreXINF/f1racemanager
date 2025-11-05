package com.zyrexinfinity.f1racemanager.factory;

import com.zyrexinfinity.f1racemanager.config.RaceSettingsProperties;
import com.zyrexinfinity.f1racemanager.simulation.RaceSettings;
import org.springframework.stereotype.Component;

@Component
public class RaceSettingsFactory {

    private final RaceSettingsProperties defaults;

    public RaceSettingsFactory(RaceSettingsProperties defaults) {
        this.defaults = defaults;
    }

    public RaceSettings create() {
        return new RaceSettings(defaults);
    }
}