package com.marius.vila.room.repository;

import com.marius.vila.room.model.Room;
import com.marius.vila.room.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByRoomType(RoomType roomType);
}
