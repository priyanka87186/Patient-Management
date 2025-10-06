package com.pm.patient_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientResponseDto {
    private String id;
    private String name;
    private String address;
    private String email;
    private String dateOfBirth;
}
