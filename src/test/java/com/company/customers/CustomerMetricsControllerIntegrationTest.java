package com.company.customers;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CustomerMetricsControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testGetCustomers() throws Exception {
        mockMvc.perform(get("/customers").with(user("testuser").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", is("Juan")))
                .andExpect(jsonPath("$[0].lastName", is("Pérez")))
                .andExpect(jsonPath("$[0].age", is(30)))
                .andExpect(jsonPath("$[0].birthDate", is("1993-05-15")))
                .andExpect(jsonPath("$[0].yearsRemainingLifeExpectancy", is(50)))
                .andExpect(jsonPath("$[1].firstName", is("Ana")))
                .andExpect(jsonPath("$[1].lastName", is("García")))
                .andExpect(jsonPath("$[1].age", is(25)))
                .andExpect(jsonPath("$[1].birthDate", is("1998-08-22")))
                .andExpect(jsonPath("$[1].yearsRemainingLifeExpectancy", is(55)))
                .andExpect(jsonPath("$[2].firstName", is("Luis")))
                .andExpect(jsonPath("$[2].lastName", is("Martínez")))
                .andExpect(jsonPath("$[2].age", is(40)))
                .andExpect(jsonPath("$[2].birthDate", is("1983-03-10")))
                .andExpect(jsonPath("$[2].yearsRemainingLifeExpectancy", is(40)));
    }

    @Test
    public void testGetCustomerMetrics() throws Exception {
        mockMvc.perform(get("/customers/metrics").with(user("testuser").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageAge").exists())
                .andExpect(jsonPath("$.ageStandardDeviation").exists());
    }
}
