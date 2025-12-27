package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "facility_scores")
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false, unique = true)
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

    public FacilityScore(Property property, Integer schoolProximity,
                         Integer hospitalProximity, Integer transportAccess,
                         Integer safetyScore) {
        this.property = property;
        this.schoolProximity = schoolProximity;
        this.hospitalProximity = hospitalProximity;
        this.transportAccess = transportAccess;
        this.safetyScore = safetyScore;
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

    public Integer getSchoolProximity() {
        return schoolProximity;
    }

    public void setSchoolProximity(Integer schoolProximity) {
        this.schoolProximity = schoolProximity;
    }

    public Integer getHospitalProximity() {
        return hospitalProximity;
    }

    public void setHospitalProximity(Integer hospitalProximity) {
        this.hospitalProximity = hospitalProximity;
    }

    public Integer getTransportAccess() {
        return transportAccess;
    }

    public void setTransportAccess(Integer transportAccess) {
        this.transportAccess = transportAccess;
    }

    public Integer getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(Integer safetyScore) {
        this.safetyScore = safetyScore;
    }
}