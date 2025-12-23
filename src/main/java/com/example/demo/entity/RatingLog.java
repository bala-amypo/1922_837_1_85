package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public RatingLog() {}

    public Long getId() { return id; }
    public String getMessage() { return message; }
    public Property getProperty() { return property; }

    public void setId(Long id) { this.id = id; }
    public void setMessage(String message) { this.message = message; }
    public void setProperty(Property property) { this.property = property; }
}