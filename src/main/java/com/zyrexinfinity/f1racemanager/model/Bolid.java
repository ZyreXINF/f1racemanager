package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.*;

@Entity
public class Bolid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bolidId;

    private String bolidModel;

    private short bolidPace;
    private short aerodynamicRating;
    private short reliability;

    public Bolid(){}
    public Bolid(long bolidId, String bolidModel, short bolidPace, short aerodynamicRating, short reliability) {
        this.bolidId = bolidId;
        this.bolidModel = bolidModel;
        this.bolidPace = bolidPace;
        this.aerodynamicRating = aerodynamicRating;
        this.reliability = reliability;
    }

    public long getBolidId() {
        return bolidId;
    }

    public String getBolidModel() {
        return bolidModel;
    }

    public void setBolidModel(String bolidModel) {
        this.bolidModel = bolidModel;
    }

    public short getBolidPace() {
        return bolidPace;
    }

    public void setBolidPace(short bolidPace) {
        this.bolidPace = bolidPace;
    }

    public short getAerodynamicRating() {
        return aerodynamicRating;
    }

    public void setAerodynamicRating(short aerodynamicRating) {
        this.aerodynamicRating = aerodynamicRating;
    }

    public short getReliability() {
        return reliability;
    }

    public void setReliability(short reliability) {
        this.reliability = reliability;
    }
}
