package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {

    Optional<FacilityScore> findByProperty(Property property);
}
