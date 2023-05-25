package edu.miu.cs.cs544.customer.service;

import edu.miu.cs.cs544.customer.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface CustomerService {

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Long customerNumber, Customer customer);

    void deleteCustomer(Long customerId);

    Customer getCustomer(Long customerId);

    Page<Customer> getAllCustomers(Pageable pageable);

}
