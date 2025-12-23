package com.example.demo.repository;

import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p WHERE p.city = ?1")
    List<Property> findByCityHql(String city);

    List<Property> findByCity(String city);
}