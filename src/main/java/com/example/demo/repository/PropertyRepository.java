package com.example.demo.repository;

import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    // HQL query
    @Query("SELECT p FROM Property p WHERE p.city = :city")
    List<Property> findByCityHql(String city);

    // Derived query
    List<Property> findByCity(String city);
}