package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Table(name = "facility_scores")
@Data
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", unique = true)
    private Property property;

    @Min(0)
    @Max(10)
    private Integer schoolProximity;

    @Min(0)
    @Max(10)
    private Integer hospitalProximity;

    @Min(0)
    @Max(10)
    private Integer transportAccess;

    @Min(0)
    @Max(10)
    private Integer safetyScore;

    public FacilityScore() {
    }

    public FacilityScore(Property property, Integer schoolProximity, Integer hospitalProximity, Integer transportAccess, Integer safetyScore) {
        this.property = property;
        this.schoolProximity = schoolProximity;
        this.hospitalProximity = hospitalProximity;
        this.transportAccess = transportAccess;
        this.safetyScore = safetyScore;
    }
}