package com.marius.vila.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marius.vila.room.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(generator = "reservationGen")
    @TableGenerator(name = "reservationGen")
    private Long id;

    @NotNull
    private Date checkInDate;

    @NotNull
    private Date checkOutDate;

    @NotNull
    private Float totalPrice;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String mentions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private List<Room> reservedRooms = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_status_id", referencedColumnName = "id")
    private ReservationStatus status;

    // TODO replace with user after user system implementation
    @NotNull
    private String surname;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;
}
