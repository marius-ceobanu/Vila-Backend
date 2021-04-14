package com.marius.vila.room.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(generator = "roomsGen")
    @TableGenerator(name = "roomGen")
    private Long id;

    @NotNull
    private String name;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @NotNull
    private Float price;
}
