package com.zyrexinfinity.f1racemanager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepo extends JpaRepository<DriverEntity, Long> {
    List<DriverEntity> findByDriverIdBetween(long startId, long endId);
}
