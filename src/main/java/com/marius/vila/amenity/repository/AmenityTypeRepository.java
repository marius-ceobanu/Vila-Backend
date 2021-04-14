package com.marius.vila.amenity.repository;

import com.marius.vila.amenity.model.AType;
import com.marius.vila.amenity.model.AmenityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityTypeRepository extends JpaRepository<AmenityType, Long> {
    AmenityType getAmenityTypeByName(AType type);
}
