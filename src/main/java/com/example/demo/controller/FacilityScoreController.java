package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreService facilityScoreService;

    public FacilityScoreController(FacilityScoreService facilityScoreService) {
        this.facilityScoreService = facilityScoreService;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> addScore(@PathVariable Long propertyId, @RequestBody FacilityScore score) {
        FacilityScore saved = facilityScoreService.addScore(propertyId, score);
        return ResponseEntity.ok(new ApiResponse(true, "FacilityScore added successfully", saved));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> getScoreByProperty(@PathVariable Long propertyId) {
        FacilityScore score = facilityScoreService.getScoreByProperty(propertyId);
        return ResponseEntity.ok(new ApiResponse(true, "FacilityScore retrieved successfully", score));
    }
}