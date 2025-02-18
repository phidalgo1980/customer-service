package com.company.customers.factory;

import com.company.customers.dto.CustomerDTO;
import com.company.customers.dto.CustomerResponseDTO;
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

    public CustomerResponseDTO createCustomerDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .age(customer.getAge())
                .birthDate(customer.getBirthDate())
                .yearsRemainingLifeExpectancy(Math.max(80 - customer.getAge(), 0))
                .build();
    }
}