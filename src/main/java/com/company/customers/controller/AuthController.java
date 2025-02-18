package com.company.customers.controller;

import com.company.customers.dto.AuthRequestDTO;
import com.company.customers.dto.AuthResponseDTO;
import com.company.customers.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        log.info("Authenticating user {}", authRequest.getUsername());

        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
