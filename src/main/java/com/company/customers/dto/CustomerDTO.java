package com.company.customers.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerDTO {

    @NotBlank(message = "{error.firstName.notBlank}")
    private String firstName;

    @NotBlank(message = "{error.lastName.notBlank}")
    private String lastName;

    @Min(value = 18, message = "{error.age.min}")
    private int age;

    @Past(message = "{error.birthDate.past}")
    private LocalDate birthDate;
}
