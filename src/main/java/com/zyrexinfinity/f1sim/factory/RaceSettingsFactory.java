package com.zyrexinfinity.f1sim.factory;

import com.zyrexinfinity.f1sim.config.RaceSettingsProperties;
import com.zyrexinfinity.f1sim.simulation.RaceSettings;
import org.springframework.stereotype.Component;

@Component
public class RaceSettingsFactory {

    private final RaceSettingsProperties defaults;

    public RaceSettingsFactory(RaceSettingsProperties defaults) {
        this.defaults = defaults;
    }

    public RaceSettings createDefaultSettings() {
        return new RaceSettings(defaults);
    }
}