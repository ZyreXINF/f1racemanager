package com.zyrexinfinity.f1racemanager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {
    List<Driver> findByDriverIdBetween(long startId, long endId);
}
