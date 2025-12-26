package com.example.demo.service.impl;

import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property addProperty(Property property) {
        if (property.getPrice() < 0 || property.getAreaSqFt() < 100) {
            throw new BadRequestException("Invalid property values");
        }
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
}
