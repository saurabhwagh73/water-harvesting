package com.waterharvest.repository;

import com.waterharvest.entity.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, Long> {
    List<ComponentEntity> findAllByOrderByStepAsc();
}