package com.company.customers.service;

import com.company.customers.dto.CustomerDTO;
import com.company.customers.dto.CustomerResponseDTO;
import com.company.customers.factory.CustomerFactory;
import com.company.customers.model.Customer;
import com.company.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.DoubleSummaryStatistics;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerFactory customerFactory;

    public Customer registerCustomer(CustomerDTO dto) {
        return customerRepository.save(customerFactory.createCustomer(dto));
    }

    public List<CustomerResponseDTO> listCustomersWithLifeExpectancy() {
        return customerRepository.findAll().stream()
                .map(customerFactory::createCustomerDTO)
                .toList();
    }


    public Map<String, Double> calculateCustomerMetrics() {
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            return Map.of("averageAge", 0.0, "ageStandardDeviation", 0.0);
        }

        DoubleSummaryStatistics stats = customers.stream()
                .mapToDouble(Customer::getAge)
                .summaryStatistics();

        double average = stats.getAverage();

        double standardDeviation = Math.sqrt(customers.stream()
                .mapToDouble(c -> Math.pow(c.getAge() - average, 2))
                .sum() / customers.size());

        return Map.of("averageAge", average, "ageStandardDeviation", standardDeviation);
    }
}
