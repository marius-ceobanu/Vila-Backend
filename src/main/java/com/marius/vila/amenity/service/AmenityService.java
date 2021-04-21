package com.marius.vila.amenity.service;

import com.marius.vila.amenity.model.AType;
import com.marius.vila.amenity.model.Amenity;
import com.marius.vila.amenity.model.AmenityType;
import com.marius.vila.amenity.repository.AmenityRepository;
import com.marius.vila.amenity.repository.AmenityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AmenityService {

    private final AmenityRepository amenityRepository;
    private final AmenityTypeRepository amenityTypeRepository;

    @Transactional
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    @Transactional
    public List<Amenity> getAllVilaAmenities() {
        AmenityType type = amenityTypeRepository.getAmenityTypeByName(AType.VILA_AMENITY);
        return amenityRepository.findAllByAmenityType(type);
    }

    public Amenity getAmenityById(Long id) {
        return amenityRepository.getOne(id);
    }
}
