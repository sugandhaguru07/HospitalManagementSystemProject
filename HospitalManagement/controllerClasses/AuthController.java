package com.hospital.management.controllerClasses;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.DTO.LoginRequest;
import com.hospital.management.DTO.LoginResponse;
import com.hospital.management.DTO.RegisterRequest;
import com.hospital.management.entityClasses.UserAccount;
import com.hospital.management.repository.UserAccountRepository;
import com.hospital.management.security.JwtUtil;
import com.hospital.management.security.Role;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;  
    private final JwtUtil jwtUtil;
    private final UserAccountRepository userRepo;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        if (userRepo.existsByUserName(req.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        UserAccount u = UserAccount.builder()
                .userName(req.getUsername())
                .encodedPassword(encoder.encode(req.getPassword()))
                .email(req.getEmail())
                .role(req.getRoles() == null || req.getRoles().isEmpty()
                        ? Set.of(Role.ROLE_USER)
                        : req.getRoles())
                .build();
        userRepo.save(u);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        var user = userRepo.findByUserName(req.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(user.getUserName(), user.getRole());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
