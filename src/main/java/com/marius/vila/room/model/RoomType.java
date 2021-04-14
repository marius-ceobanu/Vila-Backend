package com.marius.vila.room.model;

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
@Table(name = "room_types")
public class RoomType {

    @Id
    @GeneratedValue(generator = "roomTypeGen")
    @TableGenerator(name = "roomTypeGen")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type name;
}
