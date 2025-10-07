package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.model.*;
import com.zyrexinfinity.f1racemanager.repository.BolidRepo;
import com.zyrexinfinity.f1racemanager.repository.ConstructorRepo;
import com.zyrexinfinity.f1racemanager.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceSettingsService {
    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ConstructorRepo constructorRepo;

    @Autowired
    BolidRepo bolidRepo;

    //For custom grids
    private boolean driverSettingsDefault = true;
    private boolean constructorSettingsDefault = true;
    private boolean bolidSettingsDefault = true;

    public boolean initializeDriverSettings(){
        //TODO set Settings from "Grid Settings" tab
        return false;
    }
    public boolean initializeConstructorSettings(){
        //TODO set Settings from "Teams Settings" tab
        return false;
    }
    public boolean initializeBolidSettings(){
        //TODO set Settings from "Teams Settings" tab, specifically for bolids
        return false;
    }

    public List<DriverEntity> getDriverList(){
        if(driverSettingsDefault){
            return driverRepo.findByDriverIdBetween(1,20);
        }else{
            return null;
        }
    }
    public List<ConstructorEntity> getConstructorList(){
        if(constructorSettingsDefault){
            return constructorRepo.findByTeamIdBetween(1,10);
        }else{
            return null;
        }
    }
    public List<BolidEntity> getBolidList(){
        if(bolidSettingsDefault){
            return bolidRepo.findByBolidIdBetween(1,10);
        }else{
            return null;
        }
    }
}
