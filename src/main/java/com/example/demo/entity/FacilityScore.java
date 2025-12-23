package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int schoolProximity;
    private int hospitalProximity;
    private int transportAccess;
    private int safetyScore;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public FacilityScore() {}

    public Long getId() { return id; }
    public int getSchoolProximity() { return schoolProximity; }
    public int getHospitalProximity() { return hospitalProximity; }
    public int getTransportAccess() { return transportAccess; }
    public int getSafetyScore() { return safetyScore; }
    public Property getProperty() { return property; }

    public void setId(Long id) { this.id = id; }
    public void setSchoolProximity(int schoolProximity) { this.schoolProximity = schoolProximity; }
    public void setHospitalProximity(int hospitalProximity) { this.hospitalProximity = hospitalProximity; }
    public void setTransportAccess(int transportAccess) { this.transportAccess = transportAccess; }
    public void setSafetyScore(int safetyScore) { this.safetyScore = safetyScore; }
    public void setProperty(Property property) { this.property = property; }
}