package com.marius.vila;

import com.marius.vila.amenity.model.AType;
import com.marius.vila.amenity.model.Amenity;
import com.marius.vila.amenity.model.AmenityType;
import com.marius.vila.amenity.repository.AmenityRepository;
import com.marius.vila.amenity.repository.AmenityTypeRepository;
import com.marius.vila.room.model.RType;
import com.marius.vila.room.model.Room;
import com.marius.vila.room.model.RoomType;
import com.marius.vila.room.repository.RoomRepository;
import com.marius.vila.room.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class PopulateDB {

    // Populate with default data with a GET request at 'http://localhost:8080/add-data'
    private final AmenityRepository amenityRepository;
    private final AmenityTypeRepository amenityTypeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;

    public void populateDb() {

        // Default AmenityTypes
        AmenityType at1 = new AmenityType();
        at1.setName(AType.VILA_AMENITY);
        AmenityType at2 = new AmenityType();
        at2.setName(AType.ROOM_AMENITY);
        List<AmenityType> amenityTypes = Arrays.asList(at1, at2);
        for(AmenityType x : amenityTypes) {
            amenityTypeRepository.save(x);
        }

        // Default amenities
        // Default amenities names
        List<String> vilaAmenities = Arrays.asList("Cazare 9 camere", "Parcare Gratuita", "Restaurant", "Terasa", "Lounge in aer liber", "Sala conferinta", "Wi-Fi Gratuit", "Biciclete");
        List<String> roomAmenities = Arrays.asList("Wi-Fi", "Balcon", "Accesorii baie", "Flat TV", "Uscator", "Mic de jun");
        for(String i : vilaAmenities) {
            Amenity a = new Amenity();
            a.setName(i);
            a.setAmenityType(at1);
            amenityRepository.save(a);
        }
        for(String i : roomAmenities) {
            Amenity a = new Amenity();
            a.setName(i);
            a.setAmenityType(at2);
            amenityRepository.save(a);
        }

        // Default Room types
        RoomType rt1 = new RoomType();
        rt1.setType(RType.SINGLE);
        RoomType rt2 = new RoomType();
        rt2.setType(RType.DOUBLE);
        List<RoomType> roomTypes = Arrays.asList(rt1, rt2);
        for(RoomType x : roomTypes) {
            roomTypeRepository.save(x);
        }

        // Default rooms
        // Default room names
        List<String> roomNames = Arrays.asList("Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6");

        for(String x : roomNames) {
            Room room = new Room();
            room.setName(x);
            room.setDescription("The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.");
            if(x.equals("Room 5") || x.equals("Room 6")) {
                room.setRoomType(rt2);
            } else {
                room.setRoomType(rt1);
            }
            room.setPrice(250.00f);
            room.setAmenities(amenityRepository.findAllByAmenityType(at2));
            roomRepository.save(room);
        }
    }
}
