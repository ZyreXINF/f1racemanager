package com.zyrexinfinity.f1sim.factory;

import com.zyrexinfinity.f1sim.model.SessionData;
import com.zyrexinfinity.f1sim.services.GridService;
import com.zyrexinfinity.f1sim.services.RaceCalculationService;
import com.zyrexinfinity.f1sim.simulation.Driver;
import com.zyrexinfinity.f1sim.simulation.RaceSession;
import com.zyrexinfinity.f1sim.simulation.RaceSettings;
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
