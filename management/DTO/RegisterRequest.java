package com.hospital.management.DTO;

import java.util.Set;
import com.hospital.management.security.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
  @NotBlank @Size(min=3,max=50) 
  private String username;
  @NotBlank @Size(min=6,max=100) 
  private String password;
  @Email 
  private String email;
  private Set<Role> roles; 
}
