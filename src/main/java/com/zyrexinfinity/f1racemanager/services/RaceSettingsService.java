package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.model.*;
import com.zyrexinfinity.f1racemanager.repository.BolidRepo;
import com.zyrexinfinity.f1racemanager.repository.ConstructorRepo;
import com.zyrexinfinity.f1racemanager.repository.DriverRepo;
import com.zyrexinfinity.f1racemanager.simulation.Bolid;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import com.zyrexinfinity.f1racemanager.simulation.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Driver> getDriverList(){
        List<Driver> drivers = new ArrayList<>();
        if(driverSettingsDefault){
            driverRepo.findByDriverIdBetween(1,20).forEach((driverEntity) -> {
                drivers.add(new Driver(driverEntity));
            });;
            return drivers;
        }else{
            return null;
        }
    }
    public List<Team> getConstructorList(){
        List<Team> teams = new ArrayList<>();
        if(constructorSettingsDefault){
            constructorRepo.findByTeamIdBetween(1,10).forEach(constructorEntity -> {
                teams.add(new Team(constructorEntity));
            });
            return teams;
        }else{
            return null;
        }
    }
    public List<Bolid> getBolidList(){
        List<Bolid> bolids = new ArrayList<>();
        if(bolidSettingsDefault){
            bolidRepo.findByBolidIdBetween(1,10).forEach(bolidEntity -> {
                bolids.add(new Bolid(bolidEntity));
            });
            return bolids;
        }else{
            return null;
        }
    }
}
