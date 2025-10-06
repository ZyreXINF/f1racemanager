package com.zyrexinfinity.f1racemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class F1racemanagerApplication {

    public static Duration raceTime = Duration.ZERO;

	public static void main(String[] args) {
		SpringApplication.run(F1racemanagerApplication.class, args);
    }
}
