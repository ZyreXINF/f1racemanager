package com.zyrexinfinity.f1racemanager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructorRepo extends JpaRepository<ConstructorEntity,Long> {
    List<ConstructorEntity> findByTeamIdBetween(long startId, long endId);
}
