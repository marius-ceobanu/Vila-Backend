package com.marius.vila.room.service;

import com.marius.vila.room.model.Room;
import com.marius.vila.room.model.RoomType;
import com.marius.vila.room.repository.RoomRepository;
import com.marius.vila.room.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Transactional
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @Transactional
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public List<Room> getAllRoomsByType(String roomTypeId) {
        RoomType type = roomTypeRepository.getOne(Long.parseLong(roomTypeId));
        return roomRepository.findAllByRoomType(type);
    }

    @Transactional
    public Room getRoomById(String roomId) {
        return roomRepository.getOne(Long.parseLong(roomId));
    }
}
