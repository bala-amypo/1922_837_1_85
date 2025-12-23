package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    
    @PostMapping("/generate/{propertyId}")
    public RatingResult generateRating(@PathVariable Long propertyId) {
        return ratingService.generateRating(propertyId);
    }

    
    @GetMapping("/property/{propertyId}")
    public RatingResult getRating(@PathVariable Long propertyId) {
        return ratingService.getRating(propertyId);
    }
}