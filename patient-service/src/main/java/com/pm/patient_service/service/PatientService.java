package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {
    List<PatientResponseDto> getAllPatients();

    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);
}
