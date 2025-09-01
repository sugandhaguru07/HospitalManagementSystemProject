package com.hospital.management.serviceClasses;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hospital.management.entityClasses.Doctor;
import com.hospital.management.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {
  private final DoctorRepository doctorRepo;

  public Page<Doctor> getAllDoctors(Pageable pageable) { return doctorRepo.findAll(pageable); }
  public Optional<Doctor> getDoctorById(String id) { return doctorRepo.findById(id); }
  public Doctor createDoctor(Doctor doctor) { return doctorRepo.save(doctor); }
  public void deleteDoctor(String id) { doctorRepo.deleteById(id); }

  public Doctor updateDoctor(String id, Doctor doctor) {
    return doctorRepo.findById(id).map(d -> {
      d.setName(doctor.getName());
      d.setContactNum(doctor.getContactNum());
      d.setEmail(doctor.getEmail());
      d.setSpecialization(doctor.getSpecialization());
      return doctorRepo.save(d);
    }).orElseThrow(() -> new RuntimeException("Doctor not found"));
  }
}
