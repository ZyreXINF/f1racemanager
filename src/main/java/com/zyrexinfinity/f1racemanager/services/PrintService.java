package com.zyrexinfinity.f1racemanager.services;

import com.zyrexinfinity.f1racemanager.enums.DriverStatus;
import com.zyrexinfinity.f1racemanager.enums.MessageColor;
import com.zyrexinfinity.f1racemanager.simulation.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintService {
    @Value("${app.version}")
    private String appVersion;

    public static final String RESET = "\u001B[0m";
    public static final String RED = MessageColor.RED.getColor();
    public static final String YELLOW = MessageColor.YELLOW.getColor();
    public static final String GREEN = MessageColor.GREEN.getColor();
    public static final String BLUE = MessageColor.BLUE.getColor();
    public static final String CYAN = MessageColor.CYAN.getColor();
    public static final String PURPLE = MessageColor.PURPLE.getColor();

    public static <T> void printSection(String title, List<T> items) {
        System.out.println("\n========================================================================");
        System.out.println(title + ":");
        items.forEach(System.out::println);
        System.out.println("========================================================================");
    }

    public void printRaceResult(List<Driver> drivers){
        Driver driver;
        for (int i = 0; i < drivers.size(); i++) {
            driver = drivers.get(i);
            String statusColor = driver.getStatus() == DriverStatus.Finished ? GREEN : RED;
            String nameColor = driver.getStatus() == DriverStatus.Finished ? CYAN : BLUE;


            System.out.println(PURPLE + "p" + (i+1) + RESET + ": "
                    + nameColor + driver.getFullName() + RESET
                    + YELLOW + " - " + driver.getRaceTime() + RESET
                    + statusColor + " | " + driver.getStatus() + RESET);
        }
    }

    public void printDriverSessionData(Driver driver, int driverPositon){
        boolean isRacing = DriverStatus.RACING == driver.getStatus();

        String nameColor = isRacing ? CYAN : BLUE;
        String statusColor = isRacing ? GREEN : RED;
        String lapColor = isRacing ? YELLOW : RED;
        String raceTimeColor = isRacing ? RESET : RED;

        System.out.println("\n================================================");
        System.out.println("Driver: " + nameColor + driver.getFullName() + RESET);
        System.out.println("Status: " + statusColor + driver.getStatus() + RESET);

        if (isRacing) {
            System.out.println("Position: " + PURPLE + driverPositon + RESET);
        }

        System.out.println("Lap №: " + lapColor + driver.getCurrentLap() + RESET);
        System.out.println("Race time: " + raceTimeColor + driver.getRaceTime() + RESET);
        System.out.println("================================================");
    }

    public void printColoredMessage(String item, MessageColor mc){
        System.out.println(mc.getColor() + item + RESET);
    }
}
