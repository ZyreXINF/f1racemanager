package com.zyrexinfinity.f1racemanager.enums;

public enum Track {
    Monza(53, 28224,26411,26266);

    private final int lapsNimber;
    private final long sector1, sector2, sector3;

    Track(int lapsNumber, long sector1, long sector2, long sector3){
        this.lapsNimber = lapsNumber;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.sector3 = sector3;
    }

    public int getLapsNumber() {
        return lapsNimber;
    }

    public long getSectorTime(int sectorNumber){
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
