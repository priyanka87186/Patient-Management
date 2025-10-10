package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDto> getAllPatients();

    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);

    PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto);

    void deletePatient(UUID id);
}
