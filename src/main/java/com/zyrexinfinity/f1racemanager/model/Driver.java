package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.*;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;

    private String fullName;
    private String nationality;
    @ManyToOne
    @JoinColumn(name = "teamId")
    private Constructor team;

    private short driverRating;
    private short driverExperience;
    private short driverRacecraft;
    private short driverAwareness;
    private short driverPace;
}
