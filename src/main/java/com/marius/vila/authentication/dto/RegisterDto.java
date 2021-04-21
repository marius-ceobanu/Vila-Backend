package com.marius.vila.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String firstName, lastName, email, phoneNumber, password, userTypeId;
}
