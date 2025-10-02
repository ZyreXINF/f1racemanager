package com.zyrexinfinity.f1racemanager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BolidRepo extends JpaRepository<Bolid,Long> {
    List<Bolid> findByBolidIdBetween(long startId, long endId);

}
