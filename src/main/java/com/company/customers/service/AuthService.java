package com.company.customers.service;

import com.company.customers.config.SecurityConfig;
import com.company.customers.dto.AuthRequestDTO;
import com.company.customers.dto.AuthResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final SecurityConfig securityConfig;

    @Value("${security.jwt.secret}")
    private String secretKey;

    public AuthService(BCryptPasswordEncoder passwordEncoder, SecurityConfig securityConfig) {
        this.passwordEncoder = passwordEncoder;
        this.securityConfig = securityConfig;
    }

    public AuthResponseDTO authenticate(AuthRequestDTO authRequest) {
        if ("user".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            String token = securityConfig.generateJwtToken(authRequest.getUsername());
            return new AuthResponseDTO(token);
        }
        throw new RuntimeException("Invalid credentials");
    }


}
