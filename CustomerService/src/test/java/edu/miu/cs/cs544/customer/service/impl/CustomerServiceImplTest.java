package edu.miu.cs.cs544.customer.service.impl;
import edu.miu.cs.cs544.customer.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @author : JOHNNGUYEN
 * @since : 5/24/2023, Wed
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    public void testAddCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        Customer addedCustomer = customerService.addCustomer(customer);

        Assert.assertNotNull(addedCustomer);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        Customer updatedCustomer = customerService.updateCustomer(customer.getId(), customer);

    }
}
