@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreService facilityScoreService;

    // ... constructor ...

    // CREATE or UPDATE (since only one per property)
    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createOrUpdateScore(@PathVariable Long propertyId,
                                                           @RequestBody FacilityScore score) {
        FacilityScore saved = facilityScoreService.createOrUpdateScore(propertyId, score);
        return ResponseEntity.ok(new ApiResponse(true, "Facility score saved", saved));
    }

    // READ
    @GetMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> getScore(@PathVariable Long propertyId) {
        FacilityScore score = facilityScoreService.getScoreByProperty(propertyId);
        return ResponseEntity.ok(new ApiResponse(true, "Score retrieved", score));
    }

    // DELETE
    @DeleteMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteScore(@PathVariable Long propertyId) {
        facilityScoreService.deleteScore(propertyId);
        return ResponseEntity.ok(new ApiResponse(true, "Facility score deleted", null));
    }
}