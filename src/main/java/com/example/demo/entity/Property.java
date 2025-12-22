package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    @Min(value = 100, message = "Area must be at least 100 sq ft")
    private Double areaSqFt;

    // One-to-One with RatingResult (cascades delete)
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private RatingResult ratingResult;

    // One-to-Many with RatingLog (cascades delete)
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RatingLog> ratingLogs = new HashSet<>();

    // One-to-One with FacilityScore (cascades delete)
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private FacilityScore facilityScore;

    // Many-to-Many with User (assigned users)
    @ManyToMany(mappedBy = "assignedProperties")
    private Set<User> assignedUsers = new HashSet<>();

    // Constructor for creating new properties
    public Property(String title, String address, String city, Double price, Double areaSqFt) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.price = price;
        this.areaSqFt = areaSqFt;
    }
}