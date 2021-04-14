package com.marius.vila.room.model;

import com.marius.vila.amenity.model.Amenity;
import com.marius.vila.reservation.model.Reservation;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
    private List<Amenity> amenities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType roomType;

    public void addAmenity(Amenity amenity) {
        amenities.add(amenity);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
