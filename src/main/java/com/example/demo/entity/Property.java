package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Table(name = "properties")
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String address;

    private String city;

    private Double price;

    private Double areaSqFt;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private RatingResult ratingResult;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs = new ArrayList<>();

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private FacilityScore facilityScore;

    @ManyToMany(mappedBy = "assignedProperties")
    private List<User> assignedUsers = new ArrayList<>();

    public Property() {
    }

    public Property(String title, String address, String city, Double price, Double areaSqFt) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.price = price;
        this.areaSqFt = areaSqFt;
    }
}