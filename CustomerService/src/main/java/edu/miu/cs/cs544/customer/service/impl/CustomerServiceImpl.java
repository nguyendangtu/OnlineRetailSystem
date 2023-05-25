package edu.miu.cs.cs544.customer.service.impl;

import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerNumber, Customer customer) {
        Customer existingCustomer = customerRepository.findById(customerNumber).orElse(null);
        if (null != existingCustomer) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setContactNumber(customer.getContactNumber());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);

    }

}
