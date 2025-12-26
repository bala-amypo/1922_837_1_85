package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;

    public FacilityScoreServiceImpl(FacilityScoreRepository facilityScoreRepository,
                                    PropertyRepository propertyRepository) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        if (facilityScoreRepository.findByProperty(property).isPresent()) {
            throw new BadRequestException("Facility score already exists");
        }

        score.setProperty(property);
        return facilityScoreRepository.save(score);
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }
}
