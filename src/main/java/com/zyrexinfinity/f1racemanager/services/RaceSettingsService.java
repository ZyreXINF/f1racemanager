package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.model.BolidRepo;
import com.zyrexinfinity.f1racemanager.model.ConstructorRepo;
import com.zyrexinfinity.f1racemanager.model.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class RaceSettingsService {
    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ConstructorRepo constructorRepo;

    @Autowired
    BolidRepo bolidRepo;
}
