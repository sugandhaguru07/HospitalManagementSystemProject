package com.hospital.management.entityClasses;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("doctors")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Doctor {
  @Id
  private String id;
  @NotBlank @Size(min=2, max=80)
  private String name;
  @Pattern(regexp="^[0-9]{10,15}$", message="Invalid contact number")
  private String contactNum;
  @Email
  private String email;
  @NotBlank
  private String specialization;
}
