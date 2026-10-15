package com.waterharvest.repository;

import com.waterharvest.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FaqRepository extends JpaRepository<FaqEntity, Long> {
    List<FaqEntity> findAllByOrderByIdAsc();
}