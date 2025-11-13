package com.zyrexinfinity.f1sim.repository;

import com.zyrexinfinity.f1sim.model.ConstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructorRepo extends JpaRepository<ConstructorEntity,Long> {
    List<ConstructorEntity> findByTeamIdBetween(long startId, long endId);
}
