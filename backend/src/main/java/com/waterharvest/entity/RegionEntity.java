package com.waterharvest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "regions")
public class RegionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)  private String name;
    @Column(nullable = false, length = 10)   private String flag;
    @Column(nullable = false, length = 1000) private String highlightText;

    protected RegionEntity() {}
    public RegionEntity(String name, String flag, String highlightText) {
        this.name = name; this.flag = flag; this.highlightText = highlightText;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getFlag() { return flag; }
    public String getHighlightText() { return highlightText; }
}