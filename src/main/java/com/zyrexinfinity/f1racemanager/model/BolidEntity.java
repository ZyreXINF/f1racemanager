package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.*;

@Entity
@Table(name="bolid")
public class BolidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bolidId;

    private String bolidModel;

    private int aerodynamicRating;
    private int reliability;

    @Override
    public String toString() {
        return "BolidEntity{" +
                "bolidId=" + bolidId +
                ", bolidModel='" + bolidModel + '\'' +
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

    public void setBolidId(long bolidId) {
        this.bolidId = bolidId;
    }

    public int getAerodynamicRating() {
        return aerodynamicRating;
    }

    public void setAerodynamicRating(int aerodynamicRating) {
        this.aerodynamicRating = aerodynamicRating;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }
}
