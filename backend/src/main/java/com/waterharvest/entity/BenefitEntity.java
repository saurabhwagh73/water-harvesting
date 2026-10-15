package com.waterharvest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "benefits")
public class BenefitEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 10)   private String icon;
    @Column(nullable = false, length = 100)  private String title;
    @Column(nullable = false, length = 1000) private String description;

    protected BenefitEntity() {}
    public BenefitEntity(String icon, String title, String description) {
        this.icon = icon; this.title = title; this.description = description;
    }
    public Long getId() { return id; }
    public String getIcon() { return icon; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}