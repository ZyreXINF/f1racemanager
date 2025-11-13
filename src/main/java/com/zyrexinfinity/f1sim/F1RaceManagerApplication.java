package com.zyrexinfinity.f1sim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.zyrexinfinity.f1racemanager.model")
@EnableJpaRepositories(basePackages = "com.zyrexinfinity.f1racemanager.repository")
public class F1RaceManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(F1RaceManagerApplication.class, args);
    }
}
