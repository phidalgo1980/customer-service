package com.company.customers.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(18)
    private int age;

    @Past
    private LocalDate birthDate;
}
