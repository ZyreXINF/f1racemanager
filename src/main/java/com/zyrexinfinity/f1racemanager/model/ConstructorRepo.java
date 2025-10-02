package com.zyrexinfinity.f1racemanager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructorRepo extends JpaRepository<Constructor,Long> {
    List<Constructor> findByTeamIdBetween(long startId, long endId);
}
