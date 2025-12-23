package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingLogService;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingResultRepository ratingResultRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    private final RatingLogService ratingLogService;

    public RatingServiceImpl(RatingResultRepository ratingResultRepository,
                             FacilityScoreRepository facilityScoreRepository,
                             PropertyRepository propertyRepository,
                             RatingLogService ratingLogService) {
        this.ratingResultRepository = ratingResultRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
        this.ratingLogService = ratingLogService;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id " + propertyId));
        FacilityScore facilityScore = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new BadRequestException("FacilityScore required to generate rating"));
        double avgScore = (facilityScore.getSchoolProximity() + facilityScore.getHospitalProximity() +
                facilityScore.getTransportAccess() + facilityScore.getSafetyScore()) / 4.0;
        String category;
        if (avgScore < 2.5) {
            category = "POOR";
        } else if (avgScore < 5) {
            category = "AVERAGE";
        } else if (avgScore < 7.5) {
            category = "GOOD";
        } else {
            category = "EXCELLENT";
        }
        RatingResult ratingResult = new RatingResult();
        ratingResult.setProperty(property);
        ratingResult.setFinalRating(avgScore);
        ratingResult.setRatingCategory(category);
        RatingResult saved = ratingResultRepository.save(ratingResult);
        ratingLogService.addLog(propertyId, "Rating generated: " + category + " with score " + avgScore);
        return saved;
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id " + propertyId));
        return ratingResultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("RatingResult not found for property " + propertyId));
    }
}