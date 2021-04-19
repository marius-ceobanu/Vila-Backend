package com.marius.vila.room.controller;

import com.marius.vila.room.model.Room;
import com.marius.vila.room.model.RoomType;
import com.marius.vila.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("vila/v1/rooms")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class RoomController {
    private final RoomService roomService;

    @Transactional
    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @Transactional
    @GetMapping("/types")
    public List<RoomType> getAllRoomTypes() {
        return roomService.getAllRoomTypes();
    }

    @Transactional
    @GetMapping("/type/{roomTypeId}")
    public List<Room> getAllRoomsByType(@PathVariable("roomTypeId") String roomTypeId) {
        return roomService.getAllRoomsByType(roomTypeId);
    }

    @Transactional
    @GetMapping("/{roomId}")
    public Room getRoomById(@PathVariable("roomId") String roomId) {
        return roomService.getRoomById(roomId);
    }
}
