package com.epam.finaltask.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCredentialsDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registrationDate;
}
