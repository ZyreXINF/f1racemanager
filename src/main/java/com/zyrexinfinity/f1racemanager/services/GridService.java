package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GridService {
    public List<Driver> setStartingPositions(List<Driver> rawGrid){
        List<Driver> positionedGrid = new ArrayList<>(rawGrid);
        for (int i = positionedGrid.size()-1; i >= 0; i--) {
            positionedGrid.get(i).setRaceTime(i* 250L);
        }
        return positionedGrid;
    }

    public List<Driver> randomizeGrid(List<Driver> grid){
        Collections.shuffle(grid);
        return new ArrayList<>(grid);
    }

    public List<Driver> sortPositions(List<Driver> unsortedDriverList){
        List<Driver> sortedCopy = new ArrayList<>(unsortedDriverList);
        sortedCopy.sort((d1, d2) -> {
            boolean d1Dnf = d1.getStatus() == DriverStatus.CrashDNF || d1.getStatus() == DriverStatus.ReliabilityDNF || d1.getStatus() == DriverStatus.DNS;
            boolean d2Dnf = d2.getStatus() == DriverStatus.CrashDNF || d2.getStatus() == DriverStatus.ReliabilityDNF || d2.getStatus() == DriverStatus.DNS;

            if (!d1Dnf && !d2Dnf) {
                return Long.compare(d1.getRaceTime(), d2.getRaceTime());
            }

            if (d1Dnf && d2Dnf) {
                return Long.compare(d2.getRaceTime(), d1.getRaceTime());
            }

            return d1Dnf ? 1 : -1;
        });
        return sortedCopy;
    }
}
