package com.zyrexinfinity.f1sim.repository;

import com.zyrexinfinity.f1sim.model.BolidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BolidRepo extends JpaRepository<BolidEntity,Long> {
    List<BolidEntity> findByBolidIdBetween(long startId, long endId);

}
