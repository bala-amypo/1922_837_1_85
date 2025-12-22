package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // BCrypt hashed

    @Column(nullable = false)
    private String role; // ADMIN or ANALYST

    // Many-to-Many relationship with Property (assigned properties)
    @ManyToMany
    @JoinTable(
            name = "user_property_assignment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> assignedProperties = new HashSet<>();

    // Constructor for registration (used by service layer)
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}