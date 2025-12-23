package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private double price;
    private double areaSqFt;

    public Property() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public double getPrice() { return price; }
    public double getAreaSqFt() { return areaSqFt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setPrice(double price) { this.price = price; }
    public void setAreaSqFt(double areaSqFt) { this.areaSqFt = areaSqFt; }
}