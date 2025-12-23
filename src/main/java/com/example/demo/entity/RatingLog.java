package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "rating_logs")
@Data
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String message;

    private LocalDateTime loggedAt;

    @PrePersist
    protected void onCreate() {
        this.loggedAt = LocalDateTime.now();
    }

    public RatingLog() {
    }

    public RatingLog(Property property, String message, LocalDateTime loggedAt) {
        this.property = property;
        this.message = message;
        this.loggedAt = loggedAt;
    }
}