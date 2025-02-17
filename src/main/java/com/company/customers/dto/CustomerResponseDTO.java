package com.company.customers.dto;

import com.company.customers.model.Customer;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerResponseDTO {

    private String firstName;
    private String lastName;
    private int age;
    private LocalDate birthDate;
    private int yearsRemainingLifeExpectancy;

    public CustomerResponseDTO(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.age = customer.getAge();
        this.birthDate = customer.getBirthDate();
        this.yearsRemainingLifeExpectancy = Math.max(80 - customer.getAge(), 0);
    }
}

