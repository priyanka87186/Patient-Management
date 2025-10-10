package com.pm.patient_service.service.impl;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import com.pm.patient_service.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    //injecting repository dependency using constructor injection
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDto).toList();
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {


        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("Patient with this Email"+" already exists: " + patientRequestDto.getEmail());
        }
        Patient savedPatient = patientRepository.save(PatientMapper.toModel(patientRequestDto));
        return PatientMapper.toDto(savedPatient);
    }

    @Override
    public PatientResponseDto  updatePatient(UUID id, PatientRequestDto patientRequestDto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)){
            throw new EmailAlreadyExistsException("Patient with this Email"+" already exists: " + patientRequestDto.getEmail());
        }

        existingPatient.setName(patientRequestDto.getName());
        existingPatient.setEmail(patientRequestDto.getEmail());
        existingPatient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        existingPatient.setAddress(patientRequestDto.getAddress());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return PatientMapper.toDto(updatedPatient);
    }


    @Override
    public void deletePatient(UUID id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

}
