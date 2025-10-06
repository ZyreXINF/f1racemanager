package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.*;

@Entity
@Table(name="bolid")
public class BolidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bolidId;

    private String bolidModel;

    private int reliability;

    @Override
    public String toString() {
        return "Bolid{" +
                "bolidId=" + bolidId +
                ", bolidModel='" + bolidModel + '\'' +
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

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }
}
