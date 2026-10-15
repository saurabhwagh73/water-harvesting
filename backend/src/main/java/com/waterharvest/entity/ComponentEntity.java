package com.waterharvest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "components")
public class ComponentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)                private int step;
    @Column(nullable = false, length = 100)  private String name;
    @Column(nullable = false, length = 1000) private String description;

    protected ComponentEntity() {}
    public ComponentEntity(int step, String name, String description) {
        this.step = step; this.name = name; this.description = description;
    }
    public Long getId() { return id; }
    public int getStep() { return step; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}