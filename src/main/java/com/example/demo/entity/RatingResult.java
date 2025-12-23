package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double finalRating;
    private String ratingCategory;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public RatingResult() {}

    public Long getId() { return id; }
    public double getFinalRating() { return finalRating; }
    public String getRatingCategory() { return ratingCategory; }
    public Property getProperty() { return property; }

    public void setId(Long id) { this.id = id; }
    public void setFinalRating(double finalRating) { this.finalRating = finalRating; }
    public void setRatingCategory(String ratingCategory) { this.ratingCategory = ratingCategory; }
    public void setProperty(Property property) { this.property = property; }
}