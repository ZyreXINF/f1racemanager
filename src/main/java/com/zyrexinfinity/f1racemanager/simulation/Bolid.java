package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.model.BolidEntity;

import java.util.Random;

public class Bolid {
    private static final Random random = new Random();

    private String bolidModel;

    private double aerodynamicRating;
    private double reliability;

    public Bolid(BolidEntity bolidBlueprint) {
        this.bolidModel = bolidBlueprint.getBolidModel();
        this.aerodynamicRating = Math.round(((100 - bolidBlueprint.getReliability()) / 60.0) * 1000.0) / 1000.0;
        this.reliability = Math.round(((100 - bolidBlueprint.getReliability()) / 60.0) * 1000.0) / 1000.0;
    }

    public boolean checkEngineFailure(){
        double baseChance = 0.001;   // 0.1%
        double maxAdded = 0.001;     // +0.2%
        double chance = baseChance + (maxAdded * reliability);
        return random.nextDouble() < chance;
    }

    @Override
    public String toString() {
        return "Bolid{" +
                "bolidModel='" + bolidModel + '\'' +
                ", aerodynamicRating=" + aerodynamicRating +
                ", reliability=" + reliability +
                '}';
    }

    public String getBolidModel() {
        return bolidModel;
    }

    public void setBolidModel(String bolidModel) {
        this.bolidModel = bolidModel;
    }

    public double getReliability() {
        return reliability;
    }

    public void setReliability(double reliability) {
        this.reliability = Math.round(((100 - reliability) / 60.0) * 1000.0) / 1000.0;
    }

    public double getAerodynamicRating() {
        return aerodynamicRating;
    }

    public void setAerodynamicRating(double aerodynamicRating) {
        this.aerodynamicRating = Math.round(((100 - aerodynamicRating) / 50.0) * 1000.0) / 1000.0;
    }
}
