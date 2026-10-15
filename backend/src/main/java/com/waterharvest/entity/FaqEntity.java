package com.waterharvest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "faqs")
public class FaqEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)                private String question;
    @Column(nullable = false, length = 1000) private String answer;

    protected FaqEntity() {}
    public FaqEntity(String question, String answer) { this.question = question; this.answer = answer; }
    public Long getId() { return id; }
    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
}