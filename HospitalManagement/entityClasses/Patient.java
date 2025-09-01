package com.hospital.management.entityClasses;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("patients")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Patient {
  @Id
  private String id;
  @NotBlank @Size(min=2, max=80)
  private String name;
  @NotBlank
  private String gender;
  @Min(0) @Max(130)
  private int age;
}
