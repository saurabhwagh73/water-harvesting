package com.waterharvest.repository;

import com.waterharvest.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
    List<RegionEntity> findAllByOrderByIdAsc();
}