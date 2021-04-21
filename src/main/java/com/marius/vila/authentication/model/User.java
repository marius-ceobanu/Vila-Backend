package com.marius.vila.authentication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marius.vila.reservation.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "userGen")
    @TableGenerator(name = "userGen")
    private Long id;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @JsonIgnore
    @NotNull
    private String password;

    private boolean isVerified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type", referencedColumnName = "id")
    private UserType userType;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id",referencedColumnName = "id")
    private List<Reservation> reservations = new ArrayList<>();

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        roles.add(getUserType().getName().toString());
        return roles;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
