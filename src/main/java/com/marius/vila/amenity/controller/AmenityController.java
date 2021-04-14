package com.marius.vila.amenity.controller;

import com.marius.vila.amenity.model.Amenity;
import com.marius.vila.amenity.service.AmenityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vila/v1/amenities")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class AmenityController {

    private final AmenityService amenityService;

    @Transactional
    @GetMapping
    public List<Amenity> getAllAmenities() {
        return amenityService.getAllAmenities();
    }

    @Transactional
    @GetMapping("/vila")
    public List<Amenity> getAllVilaAmenities() {
        return amenityService.getAllVilaAmenities();
    }
}
