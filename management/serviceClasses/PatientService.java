package com.hospital.management.serviceClasses;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hospital.management.entityClasses.Patient;
import com.hospital.management.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
  private final PatientRepository patientRepo;

  public Page<Patient> getAllPatients(Pageable pageable) {
    return patientRepo.findAll(pageable);
  }
  public Optional<Patient> getPatientById(String id) { return patientRepo.findById(id); }
  public Patient createPatient(Patient patient) { return patientRepo.save(patient); }
  public void deletePatient(String id) { patientRepo.deleteById(id); }

  public Patient updatePatient(String id, Patient newPatient) {
    return patientRepo.findById(id).map(p -> {
      p.setName(newPatient.getName());
      p.setAge(newPatient.getAge());
      p.setGender(newPatient.getGender());
      return patientRepo.save(p);
    }).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
  }
}