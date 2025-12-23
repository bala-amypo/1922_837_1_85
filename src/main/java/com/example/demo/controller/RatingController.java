package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
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
    public ResponseEntity<ApiResponse> generateRating(@PathVariable Long propertyId) {
        RatingResult result = ratingService.generateRating(propertyId);
        return ResponseEntity.ok(new ApiResponse(true, "Rating generated successfully", result));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<ApiResponse> getRating(@PathVariable Long propertyId) {
        RatingResult result = ratingService.getRating(propertyId);
        return ResponseEntity.ok(new ApiResponse(true, "Rating retrieved successfully", result));
    }
}