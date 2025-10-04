package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.*;

@Entity
@Table(name="bolid")
public class BolidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bolidId;

    private String bolidModel;

    private short bolidPace;
    private short aerodynamicRating;
    private short reliability;

    @Override
    public String toString() {
        return "Bolid{" +
                "bolidId=" + bolidId +
                ", bolidModel='" + bolidModel + '\'' +
                ", bolidPace=" + bolidPace +
                ", aerodynamicRating=" + aerodynamicRating +
                ", reliability=" + reliability +
                '}';
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
