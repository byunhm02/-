package com.example.db_hw.repository;
import com.example.db_hw.entity.PetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetInfoRepository extends JpaRepository<PetInfo, Long> {
}
