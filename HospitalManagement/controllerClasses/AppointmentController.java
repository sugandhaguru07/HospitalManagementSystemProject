package com.hospital.management.controllerClasses;

import com.hospital.management.DTO.AppointmentRequest;
import com.hospital.management.entityClasses.Appointment;
import com.hospital.management.serviceClasses.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
  private final AppointmentService appointmentService;

  @GetMapping
  public Page<Appointment> getAllAppointments(@PageableDefault(size=10) Pageable pageable) {
    return appointmentService.getAllAppointments(pageable);
  }

  @PostMapping
  public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody AppointmentRequest request) {
    return new ResponseEntity<>(appointmentService.createAppointment(request), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public Optional<Appointment> getByID(@PathVariable String id) {
    return appointmentService.getAppointmentById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAppointmentDetails(@PathVariable String id) {
    appointmentService.deleteAppointment(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Appointment> updateAppointmentDetails(@PathVariable String id,
                                                              @Valid @RequestBody AppointmentRequest request) {
    return ResponseEntity.ok(appointmentService.updateAppointment(id, request));
  }
}
