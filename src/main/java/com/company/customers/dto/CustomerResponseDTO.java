package com.company.customers.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Builder
@ToString
public class CustomerResponseDTO {

    private String firstName;
    private String lastName;
    private int age;
    private LocalDate birthDate;
    private int yearsRemainingLifeExpectancy;
}

