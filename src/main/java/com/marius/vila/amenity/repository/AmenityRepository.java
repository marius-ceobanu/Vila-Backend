package com.marius.vila.amenity.repository;

import com.marius.vila.amenity.model.Amenity;
import com.marius.vila.amenity.model.AmenityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    List<Amenity> findAllByAmenityType(AmenityType type);
}
