package com.company.customers.controller;

import com.company.customers.dto.CustomerDTO;
import com.company.customers.dto.CustomerResponseDTO;
import com.company.customers.model.Customer;
import com.company.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerCustomer(customerDTO));
    }


    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Double>> getCustomerMetrics() {
        return ResponseEntity.ok(customerService.calculateCustomerMetrics());
    }


    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getCustomersWithLifeExpectancy() {
        return ResponseEntity.ok(customerService.listCustomersWithLifeExpectancy());
    }
}
