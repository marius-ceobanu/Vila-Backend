package com.marius.vila.amenity.service;

import com.marius.vila.amenity.model.Amenity;
import com.marius.vila.amenity.repository.AmenityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    @Transactional
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    public Amenity getAmenityById(Long id) {
        return amenityRepository.getOne(id);
    }
}
