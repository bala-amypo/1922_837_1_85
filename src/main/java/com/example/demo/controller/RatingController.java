package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/generate/{propertyId}")
    public ResponseEntity<RatingResult> generateRating(
            @PathVariable Long propertyId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ratingService.generateRating(propertyId)
        );
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<RatingResult> getRating(
            @PathVariable Long propertyId) {

        return ResponseEntity.ok(
                ratingService.getRating(propertyId)
        );
    }
}