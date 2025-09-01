package com.hospital.management.controllerClasses;

import com.hospital.management.entityClasses.Doctor;
import com.hospital.management.serviceClasses.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
  private final DoctorService doctorService;

  @GetMapping
  public Page<Doctor> getAllDoctors(@PageableDefault(size=10) Pageable pageable) {
    return doctorService.getAllDoctors(pageable);
  }

  @PostMapping
  public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
    return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public Optional<Doctor> getByID(@PathVariable String id) {
    return doctorService.getDoctorById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteDoctorDetails(@PathVariable String id) {
    doctorService.deleteDoctor(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Doctor> updateDoctorDetails(@PathVariable String id, @Valid @RequestBody Doctor doctor) {
    return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
  }
}
