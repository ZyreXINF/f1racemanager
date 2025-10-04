package com.zyrexinfinity.f1racemanager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepo extends JpaRepository<DriverEntitiy, Long> {
    List<DriverEntitiy> findByDriverIdBetween(long startId, long endId);
}
