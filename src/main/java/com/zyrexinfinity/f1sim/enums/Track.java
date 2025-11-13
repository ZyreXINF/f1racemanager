package com.zyrexinfinity.f1sim.enums;

public enum Track {
    Monza(53, 28224,26411,26266,
            "https://media.formula1.com/image/upload/c_lfill,w_3392/q_auto/v1740000000/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Italy_Circuit.webp");

    private final int lapsNimber;
    private final long sector1, sector2, sector3;
    private final String imageURL;

    Track(int lapsNumber, long sector1, long sector2, long sector3, String imageURL){
        this.lapsNimber = lapsNumber;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.sector3 = sector3;
        this.imageURL = imageURL;
    }

    public int getLapsNumber() {
        return lapsNimber;
    }

    public long getSectorTime(int sectorNumber){
        return switch (sectorNumber) {
            case 1 -> sector1;
            case 2 -> sector2;
            case 3 -> sector3;
            default -> 0;
        };
    }
    public String getImageURL(){
        return imageURL;
    }
}
