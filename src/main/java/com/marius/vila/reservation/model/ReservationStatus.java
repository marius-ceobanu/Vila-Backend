package com.marius.vila.reservation.model;

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
@Table(name = "reservation_status")
public class ReservationStatus {
    @Id
    @GeneratedValue(generator = "reservationStatusGen")
    @TableGenerator(name = "reservationStatusGen")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status name = Status.BOOKED;
}
