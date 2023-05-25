package edu.miu.cs.cs544.customer.controller;

import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : JOHNNGUYEN
 * @since : 5/24/2023, Wed
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    CustomerService customerService;

    @Test
    void getCustomer() throws Exception {
        Long accountNumber = 1l;
        Mockito.when(customerService.getCustomer(accountNumber)).thenReturn(new Customer("John", "NGUYEN", "AA@BBB.CC", "123456789"));
        mock.perform(MockMvcRequestBuilders.get("/customer/1")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"));
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void getAllCustomers() {
    }
}
