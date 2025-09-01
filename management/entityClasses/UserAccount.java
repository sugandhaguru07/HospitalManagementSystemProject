package com.hospital.management.entityClasses;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hospital.management.security.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
   @Id
   private String id;
   @Indexed(unique=true)
   @NotBlank
   private String userName;
   @NotBlank
   private String encodedPassword;
   @Email
   private String email;
   private Set<Role> role;
}
