package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintService {
    @Value("${app.version}")
    private String appVersion;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static <T> void printSection(String title, List<T> items) {
        System.out.println("\n========================================================================");
        System.out.println(title + ":");
        items.forEach(System.out::println);
        System.out.println("========================================================================");
    }

    public void printDriverSessionData(Driver driver, int driverPositon){
        System.out.println("\n================================================"
                + "\nDriver: " + YELLOW + driver.getFullName() + RESET
                + "\nPosition: " + GREEN + driverPositon + RESET
                + "\nLap №: " + PURPLE + driver.getCurrentLap() + RESET
                + "\nRace time: " + PURPLE + driver.getRaceTime() + RESET
                + "\n================================================");

    }
    public void printErrorMessage(String item){
        System.out.println(RED + item + RESET);
    }
}
