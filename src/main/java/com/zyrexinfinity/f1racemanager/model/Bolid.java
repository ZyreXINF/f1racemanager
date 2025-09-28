package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bolid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bolidId;

    private String bolidModel;

    private short bolidPace;
    private short aerodynamicRating;
    private short reliability;

}
