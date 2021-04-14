package com.marius.vila.amenity.model;

import lombok.*;

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
    private Type name;
}
