package com.hospital.management.serviceClasses;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hospital.management.DTO.AppointmentRequest;
import com.hospital.management.entityClasses.Appointment;
import com.hospital.management.repository.AppointmentRepository;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
  private final AppointmentRepository appointmentRepo;
  private final DoctorRepository doctorRepo;
  private final PatientRepository patientRepo;

  public Page<Appointment> getAllAppointments(Pageable pageable) {
    return appointmentRepo.findAll(pageable);
  }

  public Optional<Appointment> getAppointmentById(String id) {
    return appointmentRepo.findById(id);
  }

  public Appointment createAppointment(AppointmentRequest req) {
    doctorRepo.findById(req.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor not found"));
    patientRepo.findById(req.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found"));

 
    LocalDateTime start = req.getStart();
    LocalDateTime end = req.getEnd();

    if (!end.isAfter(start)) throw new RuntimeException("End must be after start");

    long mins = Duration.between(start, end).toMinutes();
    if (mins < 15) throw new RuntimeException("Minimum appointment length is 15 minutes");
    if (mins > 180) throw new RuntimeException("Maximum appointment length is 3 hours");

    
    if (start.getDayOfWeek() == DayOfWeek.SATURDAY || start.getDayOfWeek() == DayOfWeek.SUNDAY)
      throw new RuntimeException("No weekend appointments");
    if (start.getHour() < 9 || end.getHour() > 18 || (end.getHour() == 18 && end.getMinute() > 0))
      throw new RuntimeException("Appointment must be within 09:00-18:00");

 
    List<Appointment> overlaps = appointmentRepo
        .findByDoctorIdAndStartLessThanAndEndGreaterThan(req.getDoctorId(), end, start);
    if (!overlaps.isEmpty()) throw new RuntimeException("Doctor not available in this time range");

    Appointment appt = Appointment.builder()
        .doctorId(req.getDoctorId())
        .patientId(req.getPatientId())
        .start(start)
        .end(end)
        .status("CONFIRMED")
        .build();

    return appointmentRepo.save(appt);
  }

  public void deleteAppointment(String id) {
    appointmentRepo.deleteById(id);
  }

  public Appointment updateAppointment(String id, AppointmentRequest req) {
    return appointmentRepo.findById(id).map(existing -> {
      req.setDoctorId(existing.getDoctorId()); 
      Appointment updated = createAppointment(req); 

      LocalDateTime start = req.getStart();
      LocalDateTime end = req.getEnd();
      List<Appointment> overlaps = appointmentRepo
          .findByDoctorIdAndStartLessThanAndEndGreaterThan(existing.getDoctorId(), end, start)
          .stream().filter(a -> !a.getId().equals(existing.getId())).toList();
      if (!overlaps.isEmpty()) throw new RuntimeException("Doctor not available in this time range");

      existing.setStart(start);
      existing.setEnd(end);
      existing.setPatientId(req.getPatientId());
      return appointmentRepo.save(existing);
    }).orElseThrow(() -> new RuntimeException("Appointment not found"));
  }
}
