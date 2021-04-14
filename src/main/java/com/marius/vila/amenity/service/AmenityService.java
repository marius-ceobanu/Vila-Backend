package com.marius.vila.amenity.service;

import com.marius.vila.amenity.model.AType;
import com.marius.vila.amenity.model.Amenity;
import com.marius.vila.amenity.repository.AmenityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    @Transactional
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    @Transactional
    public List<Amenity> getAllVilaAmenities() {
        List<Amenity> all = getAllAmenities();
        List<Amenity> villa = new ArrayList<>();
        for(Amenity x : all) {
            if(x.getAmenityType().getName().equals(AType.VILA_AMENITY)) {
                villa.add(x);
            }
        }
        return villa;
    }

    public Amenity getAmenityById(Long id) {
        return amenityRepository.getOne(id);
    }
}
