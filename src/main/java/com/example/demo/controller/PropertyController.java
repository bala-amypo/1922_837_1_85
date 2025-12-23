package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Property;
import com.example.demo.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProperty(@RequestBody Property property) {
        Property saved = propertyService.addProperty(property);
        return ResponseEntity.ok(new ApiResponse(true, "Property added successfully", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(new ApiResponse(true, "Properties retrieved successfully", properties));
    }
}