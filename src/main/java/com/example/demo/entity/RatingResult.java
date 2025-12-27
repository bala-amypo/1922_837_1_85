package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating_results")
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false, unique = true)
    private Property property;

    private Double finalRating;

    private String ratingCategory;

    private LocalDateTime ratedAt;

    public RatingResult() {
    }

    public RatingResult(Property property, Double finalRating,
                        String ratingCategory, LocalDateTime ratedAt) {
        this.property = property;
        this.finalRating = finalRating;
        this.ratingCategory = ratingCategory;
        this.ratedAt = ratedAt;
    }

    @PrePersist
    public void onCreate() {
        this.ratedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Double getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(Double finalRating) {
        this.finalRating = finalRating;
    }

    public String getRatingCategory() {
        return ratingCategory;
    }

    public void setRatingCategory(String ratingCategory) {
        this.ratingCategory = ratingCategory;
    }

    public LocalDateTime getRatedAt() {
        return ratedAt;
    }
}