package com.pm.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientRequestDto {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be at most 100 characters long")
    private String name;

    @NotBlank(message = "Address is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is mandatory")
    private String dateOfBirth;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Registered date is mandatory")
    private String registeredDate;
}

