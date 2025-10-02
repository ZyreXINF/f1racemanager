package com.zyrexinfinity.f1racemanager.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintService {
    public static <T> void printSection(String title, List<T> items) {
        System.out.println("\n========================================================================");
        System.out.println(title + ":");
        items.forEach(System.out::println);
        System.out.println("========================================================================");
    }
}
