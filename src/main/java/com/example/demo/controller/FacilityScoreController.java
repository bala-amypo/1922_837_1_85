package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<FacilityScore> addScore(
            @PathVariable Long propertyId,
            @RequestBody FacilityScore score) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                facilityScoreService.addScore(propertyId, score)
        );
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> getScore(
            @PathVariable Long propertyId) {

        return ResponseEntity.ok(
                facilityScoreService.getScoreByProperty(propertyId)
        );
    }
}