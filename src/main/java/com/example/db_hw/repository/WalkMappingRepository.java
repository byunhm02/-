package com.example.db_hw.repository;

import com.example.db_hw.entity.WalkMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalkMappingRepository extends JpaRepository<WalkMapping, Long> {
    List<WalkMapping> findByRegionAndWalkTime(String region, String walkTime);
}
