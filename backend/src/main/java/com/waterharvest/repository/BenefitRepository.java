package com.waterharvest.repository;

import com.waterharvest.entity.BenefitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BenefitRepository extends JpaRepository<BenefitEntity, Long> {
    List<BenefitEntity> findAllByOrderByIdAsc();
}