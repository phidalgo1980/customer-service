package com.company.customers.service;

import com.company.customers.exception.UserNotFoundException;
import com.company.customers.security.SecurityConfig;
import com.company.customers.dto.AuthRequestDTO;
import com.company.customers.dto.AuthResponseDTO;

import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final SecurityConfig securityConfig;


    public AuthService(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    public AuthResponseDTO authenticate(AuthRequestDTO authRequest) {
        if ("user".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            String token = securityConfig.generateJwtToken(authRequest.getUsername());
            return new AuthResponseDTO(token);
        }
        throw new UserNotFoundException();
    }

}
