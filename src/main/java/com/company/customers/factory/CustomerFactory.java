package com.company.customers.factory;

import com.company.customers.dto.CustomerDTO;
import com.company.customers.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {

    public Customer createCustomer(CustomerDTO dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .birthDate(dto.getBirthDate())
                .build();
    }
}