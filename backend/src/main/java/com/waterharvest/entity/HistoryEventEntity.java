package com.waterharvest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "history_events")
public class HistoryEventEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)                private int sortOrder;
    @Column(nullable = false, length = 50)   private String periodLabel;
    @Column(nullable = false, length = 100)  private String place;
    @Column(nullable = false, length = 1000) private String description;

    protected HistoryEventEntity() {}
    public HistoryEventEntity(int sortOrder, String periodLabel, String place, String description) {
        this.sortOrder = sortOrder; this.periodLabel = periodLabel; this.place = place; this.description = description;
    }
    public Long getId() { return id; }
    public int getSortOrder() { return sortOrder; }
    public String getPeriodLabel() { return periodLabel; }
    public String getPlace() { return place; }
    public String getDescription() { return description; }
}