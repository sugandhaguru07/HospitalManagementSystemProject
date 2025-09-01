package com.hospital.management.entityClasses;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("appointments")
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
public class Appointment {
  @Id
  private String id;
  @NotBlank
  private String patientId;
  @NotBlank
  private String doctorId;
  @NotNull
  private LocalDateTime start;
  @NotNull
  private LocalDateTime end;
  private String status;
}