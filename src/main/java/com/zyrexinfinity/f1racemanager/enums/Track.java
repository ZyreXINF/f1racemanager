package com.zyrexinfinity.f1racemanager.enums;

import java.time.Duration;

public enum Track {
    Spa_Francorchamps(Duration.ofSeconds(105), 44),
    Monza(Duration.ofSeconds(82),53),
    Monaco(Duration.ofSeconds(74), 78);


    private final Duration lapTime;
    private final int lapsNumber;

    Track(Duration lapTime, int lapsNumber){
        this.lapTime = lapTime;
        this.lapsNumber = lapsNumber;
    }

    public long getLapTime() {
        return lapTime.getSeconds();
    }

    public int getLapsNumber() {
        return lapsNumber;
    }
}
