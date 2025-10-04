package com.zyrexinfinity.f1racemanager.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintService {
    @Value("${app.version}")
    private String appVersion;

    public static <T> void printSection(String title, List<T> items) {
        System.out.println("\n========================================================================");
        System.out.println(title + ":");
        items.forEach(System.out::println);
        System.out.println("========================================================================");
    }
    public void printErrorMessage(String item){
        System.out.println("\u001B[31m" + item + "\u001B[0m");
    }
}
