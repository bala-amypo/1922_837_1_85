package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RatingLog> addLog(
            @PathVariable Long propertyId,
            @RequestBody String message) {

        return ResponseEntity.ok(
                ratingLogService.addLog(propertyId, message)
        );
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<List<RatingLog>> getLogs(
            @PathVariable Long propertyId) {

        return ResponseEntity.ok(
                ratingLogService.getLogsByProperty(propertyId)
        );
    }
}