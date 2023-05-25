package edu.miu.cs.cs544.customer.repository;

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
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindById() {
        Customer customer = new Customer();
        customer.setLastName("John Doe");
        customerRepository.save(customer);
        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        Assert.assertNotNull(foundCustomer);
        Assert.assertEquals(customer.getFirstName()
                , foundCustomer.getFirstName());
    }



}
