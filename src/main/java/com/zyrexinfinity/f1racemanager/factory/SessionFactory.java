package com.zyrexinfinity.f1racemanager.factory;

import com.zyrexinfinity.f1racemanager.model.SessionData;
import com.zyrexinfinity.f1racemanager.services.GridService;
import com.zyrexinfinity.f1racemanager.services.RaceCalculationService;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import com.zyrexinfinity.f1racemanager.simulation.RaceSession;
import com.zyrexinfinity.f1racemanager.simulation.RaceSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionFactory {
    @Autowired
    private RaceCalculationService calc;
    @Autowired
    private GridService grid;

    public RaceSession createRaceSession(RaceSettings settings, List<Driver> drivers) {
        return new RaceSession(settings, drivers, calc, grid);
    }
    public SessionData createSessionData(RaceSession session){
        return new SessionData(session);
    }
    //create SessionData here
}
