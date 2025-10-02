package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.model.*;
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

    public List<Driver> getDriverSettings(){
        if(driverSettingsDefault){
            return driverRepo.findByDriverIdBetween(1,20);
        }else{
            return null;
        }
    }
    public List<Constructor> getConstructorSettings(){
        if(constructorSettingsDefault){
            return constructorRepo.findByTeamIdBetween(1,10);
        }else{
            return null;
        }
    }
    public List<Bolid> getBolidSettings(){
        if(bolidSettingsDefault){
            return bolidRepo.findByBolidIdBetween(1,10);
        }else{
            return null;
        }
    }
}
