package com.hospital.management.controllerClasses;

import com.hospital.management.entityClasses.Patient;
import com.hospital.management.serviceClasses.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
  private final PatientService patientService;

  @GetMapping
  public Page<Patient> getAllPatients(@PageableDefault(size = 10) Pageable pageable) {
    return patientService.getAllPatients(pageable);
  }

  @PostMapping
  public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
    return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public Optional<Patient> getByID(@PathVariable String id) {
    return patientService.getPatientById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePatientDetails(@PathVariable String id) {
    patientService.deletePatient(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Patient> updatePatientDetails(@PathVariable String id, @Valid @RequestBody Patient patient) {
    return ResponseEntity.ok(patientService.updatePatient(id, patient));
  }
}
