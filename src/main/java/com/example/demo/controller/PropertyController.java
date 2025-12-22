package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Property;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // CREATE
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createProperty(@RequestBody Property property) {
        Property saved = propertyService.addProperty(property);
        return ResponseEntity.ok(new ApiResponse(true, "Property created successfully", saved));
    }

    // READ - All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(new ApiResponse(true, "Properties retrieved", properties));
    }

    // READ - Single
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Property retrieved", property));
    }

    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        Property updated = propertyService.updateProperty(id, property);
        return ResponseEntity.ok(new ApiResponse(true, "Property updated successfully", updated));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok(new ApiResponse(true, "Property deleted successfully", null));
    }
}