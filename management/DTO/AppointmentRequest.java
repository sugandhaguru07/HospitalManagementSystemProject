package com.hospital.management.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class AppointmentRequest {
	@NotBlank 
	private String patientId;
	@NotBlank 
	private String doctorId;
	@NotNull  
	private LocalDateTime start;
	@NotNull  
	private LocalDateTime end;
}
