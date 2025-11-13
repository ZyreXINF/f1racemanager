package com.zyrexinfinity.f1sim.services;

import com.zyrexinfinity.f1sim.repository.DriverRepo;
import com.zyrexinfinity.f1sim.simulation.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    RaceSettingsService settingsService;

    public List<Driver> fetchDrivers(){
        try{
            List<Driver> drivers = new ArrayList<>();
            driverRepo.findByDriverIdBetween(1,20).forEach((driverEntity) -> {
                drivers.add(new Driver(driverEntity));
            });
            return drivers;
        }catch (Exception e){
            return null;
        }
    }
}
