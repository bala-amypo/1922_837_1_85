package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class RatingLogController {

    private final RatingLogService ratingLogService;

    public RatingLogController(RatingLogService ratingLogService) {
        this.ratingLogService = ratingLogService;
    }

    
    @PostMapping("/{propertyId}")
    public RatingLog addLog(
            @PathVariable Long propertyId,
            @RequestParam String message) {

        return ratingLogService.addLog(propertyId, message);
    }

    
    @GetMapping("/{propertyId}")
    public List<RatingLog> getLogs(@PathVariable Long propertyId) {
        return ratingLogService.getLogs(propertyId);
    }
}