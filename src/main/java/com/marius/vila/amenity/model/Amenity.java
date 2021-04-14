package com.marius.vila.amenity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "amenities")
public class Amenity {
    @Id
    @GeneratedValue(generator = "amenityGen")
    @TableGenerator(name = "amenityGen")
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_type_id", referencedColumnName = "id")
    private AmenityType amenityType;
}
