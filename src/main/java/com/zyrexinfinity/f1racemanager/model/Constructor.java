package com.zyrexinfinity.f1racemanager.model;

import jakarta.persistence.*;

@Entity
public class Constructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;
    private String teamName;

    private short crewRating; //affect overall pitstop time rating

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bolidId")
    private Bolid bolid;
}