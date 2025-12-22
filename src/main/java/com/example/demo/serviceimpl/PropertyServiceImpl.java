// ... existing imports ...

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property addProperty(Property property) {
        validateProperty(property);
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
    }

    @Override
    public Property updateProperty(Long id, Property updatedProperty) {
        Property existing = getPropertyById(id);
        existing.setTitle(updatedProperty.getTitle());
        existing.setAddress(updatedProperty.getAddress());
        existing.setCity(updatedProperty.getCity());
        existing.setPrice(updatedProperty.getPrice());
        existing.setAreaSqFt(updatedProperty.getAreaSqFt());
        validateProperty(existing);
        return propertyRepository.save(existing);
    }

    @Override
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Property not found with id: " + id);
        }
        propertyRepository.deleteById(id); // Cascades to RatingResult, FacilityScore, RatingLogs
    }

    private void validateProperty(Property property) {
        if (property.getPrice() < 0) {
            throw new BadRequestException("Price must be >= 0");
        }
        if (property.getAreaSqFt() < 100) {
            throw new BadRequestException("Area must be at least 100 sq ft");
        }
    }
}