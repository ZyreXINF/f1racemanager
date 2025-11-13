package com.zyrexinfinity.f1sim.services;

import com.zyrexinfinity.f1sim.enums.*;
import com.zyrexinfinity.f1sim.factory.SessionFactory;
import com.zyrexinfinity.f1sim.simulation.Driver;
import com.zyrexinfinity.f1sim.simulation.RaceSession;
import com.zyrexinfinity.f1sim.simulation.RaceSettings;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RaceService {
    @Autowired
    private GridService gridService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RaceSettingsService raceSettingsService;
    @Autowired
    private SessionFactory raceSessionFactory;

    private RaceSession session;
    private ScheduledExecutorService scheduler;


    public boolean initRace() {
        if (Objects.isNull(session)) {
            RaceSettings settings;
            if(Objects.isNull(raceSettingsService.getSettings())){
                settings = raceSettingsService.getDefaultSettings();
            }else{
                settings = raceSettingsService.getSettings();
            }

            List<Driver> drivers = driverService.fetchDrivers();
            drivers = gridService.randomizeGrid(drivers);
            drivers = gridService.setStartingPositions(drivers);

            session = raceSessionFactory.createRaceSession(settings, drivers);
            session.setRaceStatus(RaceStatus.READY);
            return true;
        }
        return false;
    }

    public void startRace() {
        if (!Objects.isNull(session) && session.getRaceStatus() == RaceStatus.READY) {
            session.setRaceStatus(RaceStatus.RACING);
            session.getDriversList().forEach(driver -> {
                driver.setStatus(DriverStatus.RACING);
            });
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                if(!session.update()){
                    shutdownScheduler();
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
    }

    @PreDestroy
    private void shutdownScheduler() {
        scheduler.shutdown();
    }

    public void setSession(RaceSession session) {
        this.session = session;
    }

    public RaceSession getSession() {
        return session;
    }
}