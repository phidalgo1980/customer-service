package com.company.customers;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.company.customers.model.Customer;
import com.company.customers.repository.CustomerRepository;
import com.company.customers.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void testCalculateCustomerMetrics() {
        // Configurar datos de prueba
        List<Customer> customers = List.of(
                Customer.builder()
                        .firstName("Juan")
                        .lastName("Pérez")
                        .age(25)
                        .birthDate(LocalDate.parse("2000-01-01"))
                        .build(),
                Customer.builder()
                        .firstName("Francisco")
                        .lastName("Hernandez")
                        .age(41)
                        .birthDate(LocalDate.parse("1983-07-01"))
                        .build()
        );

        // Simular comportamiento del repositorio
        when(customerRepository.findAll()).thenReturn(customers);

        // Ejecutar el método a probar
        Map<String, Double> metrics = customerService.calculateCustomerMetrics();

        // Verificar los resultados
        assertThat(metrics.get("averageAge")).isEqualTo(33.0);
        assertThat(metrics.get("ageStandardDeviation")).isPositive();
    }
}

