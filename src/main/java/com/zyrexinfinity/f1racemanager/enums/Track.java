package com.zyrexinfinity.f1racemanager.enums;

public enum Track {
    Monza(53, 26243,26311,26266);

    private final int lapsNimber;
    //avg time for sector = fastest sector time set + 0.75 seconds, time set in nanoseconds
    private final long sector1, sector2, sector3;

    Track(int lapsNumber, long sector1, long sector2, long sector3){
        this.lapsNimber = lapsNumber;
        this.sector1 = sector1 + 1000;
        this.sector2 = sector2 + 1000;
        this.sector3 = sector3 + 1000;
    }

    public int getLapsNumber() {
        return lapsNimber;
    }

    public long getSectorTime(byte sectorNumber){
        switch (sectorNumber){
            case 1:
                return sector1;
            case 2:
                return sector2;
            case 3:
                return sector3;
            default:
                return 0;
        }
    }
}
