package com.marius.vila.room.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private Long id;
    private String name;
    private String description;
    private String price;
    private List<Long> amenitiesIds;
}
