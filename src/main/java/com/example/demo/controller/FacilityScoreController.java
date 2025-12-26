package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreService service;

    public FacilityScoreController(FacilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> createScore(
            @PathVariable Long propertyId,
            @RequestBody FacilityScore score) {
        return ResponseEntity.status(201).body(service.addScore(propertyId, score));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
        return ResponseEntity.ok(service.getScoreByProperty(propertyId));
    }
}
