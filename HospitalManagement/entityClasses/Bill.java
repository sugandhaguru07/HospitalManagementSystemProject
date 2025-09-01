package com.hospital.management.entityClasses;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("bills")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Bill {
  @Id
  private String id;
  @NotBlank
  private String patientId;
  @Positive
  private Long amount;
  @NotBlank
  private String status; 
  @NotNull
  private LocalDate date;
}
