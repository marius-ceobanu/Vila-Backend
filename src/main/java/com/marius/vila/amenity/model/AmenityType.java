package com.marius.vila.amenity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "amenity_types")
public class AmenityType {
    @Id
    @GeneratedValue(generator = "amenityTypeGen")
    @TableGenerator(name = "amenityTypeGen")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AType name;
}
