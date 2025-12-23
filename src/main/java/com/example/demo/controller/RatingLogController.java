package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class RatingLogController {

    private final RatingLogService ratingLogService;

    public RatingLogController(RatingLogService ratingLogService) {
        this.ratingLogService = ratingLogService;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> addLog(@PathVariable Long propertyId, @RequestBody Map<String, String> request) {
        String message = request.get("message");
        RatingLog log = ratingLogService.addLog(propertyId, message);
        return ResponseEntity.ok(new ApiResponse(true, "Log added successfully", log));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> getLogsByProperty(@PathVariable Long propertyId) {
        List<RatingLog> logs = ratingLogService.getLogsByProperty(propertyId);
        return ResponseEntity.ok(new ApiResponse(true, "Logs retrieved successfully", logs));
    }
}