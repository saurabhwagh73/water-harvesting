package com.waterharvest.repository;

import com.waterharvest.entity.HistoryEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistoryEventRepository extends JpaRepository<HistoryEventEntity, Long> {
    List<HistoryEventEntity> findAllByOrderBySortOrderAsc();
}